package com.cricket.controller;

import com.cricket.dto.*;
import com.cricket.service.MatchService;
import com.cricket.service.SseEmitterService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "*")
public class MatchController {

    private final MatchService matchService;
    private final SseEmitterService sseEmitterService;

    public MatchController(MatchService matchService, SseEmitterService sseEmitterService) {
        this.matchService = matchService;
        this.sseEmitterService = sseEmitterService;
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("/live")
    public ResponseEntity<List<MatchDTO>> getLiveMatches() {
        return ResponseEntity.ok(matchService.getLiveMatches());
    }

    @GetMapping("/scheduled")
    public ResponseEntity<List<MatchDTO>> getScheduledMatches() {
        return ResponseEntity.ok(matchService.getScheduledMatches());
    }

    @GetMapping("/completed")
    public ResponseEntity<List<MatchDTO>> getCompletedMatches() {
        return ResponseEntity.ok(matchService.getCompletedMatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@Valid @RequestBody CreateMatchRequest request) {
        return ResponseEntity.ok(matchService.createMatch(request));
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<MatchDTO> startMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.startMatch(id));
    }

    @PostMapping("/{id}/start-innings2")
    public ResponseEntity<MatchDTO> startSecondInnings(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.startSecondInnings(id));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<MatchDTO> completeMatch(@PathVariable Long id,
                                                   @RequestBody Map<String, String> body) {
        String result = body.getOrDefault("result", "Match completed");
        return ResponseEntity.ok(matchService.completeMatch(id, result));
    }

    @PostMapping("/score-update")
    public ResponseEntity<MatchDTO> postScoreUpdate(@Valid @RequestBody ScoreUpdateRequest request) {
        return ResponseEntity.ok(matchService.postScoreUpdate(request));
    }

    @GetMapping("/{id}/scorecard/{innings}")
    public ResponseEntity<List<PlayerMatchStatDTO>> getScorecard(
            @PathVariable Long id,
            @PathVariable Integer innings) {
        return ResponseEntity.ok(matchService.getScorecard(id, innings));
    }

    @GetMapping("/{id}/commentary")
    public ResponseEntity<List<ScoreUpdateDTO>> getCommentary(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getCommentary(id));
    }

    @GetMapping("/{id}/commentary/recent")
    public ResponseEntity<List<ScoreUpdateDTO>> getRecentCommentary(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getRecentCommentary(id));
    }

    @GetMapping(value = "/{id}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamMatch(@PathVariable Long id) {
        return sseEmitterService.subscribeToMatch(id);
    }

    @GetMapping(value = "/stream/global", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamAllMatches() {
        return sseEmitterService.subscribeGlobal();
    }
}
