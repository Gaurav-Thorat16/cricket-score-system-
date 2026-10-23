package com.cricket.service;

import com.cricket.dto.MatchDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseEmitterService {

    private static final Logger log = LoggerFactory.getLogger(SseEmitterService.class);

    private final Map<Long, List<SseEmitter>> matchEmitters = new ConcurrentHashMap<>();
    private final List<SseEmitter> globalEmitters = new CopyOnWriteArrayList<>();

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public SseEmitter subscribeToMatch(Long matchId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        matchEmitters.computeIfAbsent(matchId, k -> new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> removeMatchEmitter(matchId, emitter));
        emitter.onTimeout(() -> removeMatchEmitter(matchId, emitter));
        emitter.onError(e -> removeMatchEmitter(matchId, emitter));

        log.debug("New SSE subscriber for match {}", matchId);
        return emitter;
    }

    public SseEmitter subscribeGlobal() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        globalEmitters.add(emitter);
        emitter.onCompletion(() -> globalEmitters.remove(emitter));
        emitter.onTimeout(() -> globalEmitters.remove(emitter));
        emitter.onError(e -> globalEmitters.remove(emitter));
        log.debug("New global SSE subscriber. Total: {}", globalEmitters.size());
        return emitter;
    }

    public void sendMatchUpdate(MatchDTO matchDTO) {
        String json;
        try {
            json = objectMapper.writeValueAsString(matchDTO);
        } catch (Exception e) {
            log.error("Failed to serialize match update", e);
            return;
        }

        List<SseEmitter> emitters = matchEmitters.getOrDefault(matchDTO.getId(), new ArrayList<>());
        List<SseEmitter> deadEmitters = new ArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("score-update")
                        .data(json));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);

        List<SseEmitter> deadGlobal = new ArrayList<>();
        for (SseEmitter emitter : globalEmitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("live-update")
                        .data(json));
            } catch (IOException e) {
                deadGlobal.add(emitter);
            }
        }
        globalEmitters.removeAll(deadGlobal);
    }

    private void removeMatchEmitter(Long matchId, SseEmitter emitter) {
        List<SseEmitter> emitters = matchEmitters.get(matchId);
        if (emitters != null) emitters.remove(emitter);
    }
}
