package com.cricket.service;

import com.cricket.dto.MatchDTO;
import com.cricket.dto.ScoreUpdateRequest;
import com.cricket.model.*;
import com.cricket.repository.MatchRepository;
import com.cricket.repository.PlayerMatchStatRepository;
import com.cricket.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LiveScoreSimulator {

    private static final Logger log = LoggerFactory.getLogger(LiveScoreSimulator.class);

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final PlayerMatchStatRepository playerMatchStatRepository;
    private final MatchService matchService;
    private final SseEmitterService sseEmitterService;

    private final Random random = new Random();

    private final Map<Long, long[]> currentBatsmen = new HashMap<>();
    private final Map<Long, Long> currentBowler = new HashMap<>();
    private final Map<Long, Integer> ballsThisOver = new HashMap<>();
    private final Map<Long, Integer> batsmanIndex = new HashMap<>();
    private final Map<Long, Integer> bowlerIndex = new HashMap<>();

    public LiveScoreSimulator(MatchRepository matchRepository,
                              PlayerRepository playerRepository,
                              PlayerMatchStatRepository playerMatchStatRepository,
                              MatchService matchService,
                              SseEmitterService sseEmitterService) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.playerMatchStatRepository = playerMatchStatRepository;
        this.matchService = matchService;
        this.sseEmitterService = sseEmitterService;
    }

    @Scheduled(fixedDelay = 3000)
    public void simulateBall() {
        List<Match> liveMatches = matchRepository.findAllLiveMatches();
        for (Match match : liveMatches) {
            try {
                processOneBall(match);
            } catch (Exception e) {
                log.warn("Simulation error for match {}: {}", match.getId(), e.getMessage());
            }
        }
    }

    private void processOneBall(Match match) {
        Long matchId = match.getId();
        List<Player> battingTeamPlayers = getTeamPlayers(match, match.getBattingTeam());
        List<Player> bowlingTeamPlayers = getTeamPlayers(match, match.getBowlingTeam());

        if (battingTeamPlayers.isEmpty() || bowlingTeamPlayers.isEmpty()) return;

        if (!currentBatsmen.containsKey(matchId)) {
            currentBatsmen.put(matchId, new long[]{
                    battingTeamPlayers.get(0).getId(),
                    battingTeamPlayers.get(1).getId()
            });
            batsmanIndex.put(matchId, 2);
        }
        if (!currentBowler.containsKey(matchId)) {
            currentBowler.put(matchId, bowlingTeamPlayers.get(0).getId());
            bowlerIndex.put(matchId, 1);
            ballsThisOver.put(matchId, 0);
        }

        long[] batsmen = currentBatsmen.get(matchId);
        long bowlerId = currentBowler.get(matchId);

        DeliveryResult result = simulateDelivery();

        try {
            ScoreUpdateRequest req = ScoreUpdateRequest.builder()
                    .matchId(matchId)
                    .batsmanId(batsmen[0])
                    .bowlerId(bowlerId)
                    .runsScored(result.runs)
                    .isWicket(result.isWicket)
                    .wicketType(result.wicketType)
                    .isWide(result.isWide)
                    .isNoBall(result.isNoBall)
                    .build();

            MatchDTO updatedMatch = matchService.postScoreUpdate(req);

            if (result.isWicket) {
                int idx = batsmanIndex.getOrDefault(matchId, 2);
                if (idx < battingTeamPlayers.size()) {
                    batsmen[0] = battingTeamPlayers.get(idx).getId();
                    batsmanIndex.put(matchId, idx + 1);
                } else {
                    log.info("All out! Match {} innings ending...", matchId);
                    currentBatsmen.remove(matchId);
                    currentBowler.remove(matchId);
                    ballsThisOver.remove(matchId);
                    batsmanIndex.remove(matchId);
                    bowlerIndex.remove(matchId);
                    sseEmitterService.sendMatchUpdate(updatedMatch);
                    return;
                }
            }

            if (!result.isWide && result.runs % 2 == 1) {
                long tmp = batsmen[0]; batsmen[0] = batsmen[1]; batsmen[1] = tmp;
            }

            if (!result.isWide && !result.isNoBall) {
                int balls = ballsThisOver.getOrDefault(matchId, 0) + 1;
                if (balls >= 6) {
                    long tmp = batsmen[0]; batsmen[0] = batsmen[1]; batsmen[1] = tmp;
                    int bIdx = bowlerIndex.getOrDefault(matchId, 1);
                    long nextBowler = bowlingTeamPlayers.get(bIdx % bowlingTeamPlayers.size()).getId();
                    if (nextBowler == bowlerId && bowlingTeamPlayers.size() > 1) {
                        bIdx++;
                        nextBowler = bowlingTeamPlayers.get(bIdx % bowlingTeamPlayers.size()).getId();
                    }
                    currentBowler.put(matchId, nextBowler);
                    bowlerIndex.put(matchId, bIdx + 1);
                    ballsThisOver.put(matchId, 0);
                } else {
                    ballsThisOver.put(matchId, balls);
                }
            }

            if ("INNINGS_BREAK".equals(updatedMatch.getStatus().name()) && match.getCurrentInnings() == 1) {
                currentBatsmen.remove(matchId);
                currentBowler.remove(matchId);
                ballsThisOver.remove(matchId);
                batsmanIndex.remove(matchId);
                bowlerIndex.remove(matchId);
            }

            sseEmitterService.sendMatchUpdate(updatedMatch);

        } catch (Exception e) {
            log.warn("Failed to post score update for match {}: {}", matchId, e.getMessage());
        }
    }

    private DeliveryResult simulateDelivery() {
        DeliveryResult r = new DeliveryResult();

        if (random.nextInt(100) < 5) { r.isWide = true; r.runs = 1; return r; }
        if (random.nextInt(100) < 3) { r.isNoBall = true; r.runs = 0; return r; }

        if (random.nextInt(100) < 7) {
            r.isWicket = true;
            r.runs = 0;
            String[] types = {"Bowled", "Caught", "LBW", "Run Out", "Stumped"};
            int[] weights  = {25, 40, 20, 10, 5};
            r.wicketType = weightedPick(types, weights);
            return r;
        }

        int[] runOptions = {0, 1, 2, 3, 4, 6};
        int[] weights    = {30, 30, 15, 5, 15, 5};
        r.runs = weightedPick(runOptions, weights);
        return r;
    }

    private <T> T weightedPick(T[] items, int[] weights) {
        int total = Arrays.stream(weights).sum();
        int pick = random.nextInt(total);
        int cumulative = 0;
        for (int i = 0; i < items.length; i++) {
            cumulative += weights[i];
            if (pick < cumulative) return items[i];
        }
        return items[items.length - 1];
    }

    private int weightedPick(int[] items, int[] weights) {
        Integer[] boxed = new Integer[items.length];
        for (int i = 0; i < items.length; i++) boxed[i] = items[i];
        return weightedPick(boxed, weights);
    }

    private List<Player> getTeamPlayers(Match match, String teamShortName) {
        if (match.getTeam1().getShortName().equals(teamShortName)) {
            return playerRepository.findByTeamId(match.getTeam1().getId());
        } else {
            return playerRepository.findByTeamId(match.getTeam2().getId());
        }
    }

    static class DeliveryResult {
        int runs = 0;
        boolean isWicket = false;
        String wicketType = null;
        boolean isWide = false;
        boolean isNoBall = false;
    }
}
