package com.cricket.service;

import com.cricket.dto.*;
import com.cricket.model.*;
import com.cricket.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private static final Logger log = LoggerFactory.getLogger(MatchService.class);

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PlayerMatchStatRepository playerMatchStatRepository;
    private final ScoreUpdateRepository scoreUpdateRepository;
    private final TeamService teamService;

    public MatchService(MatchRepository matchRepository,
                        TeamRepository teamRepository,
                        PlayerRepository playerRepository,
                        PlayerMatchStatRepository playerMatchStatRepository,
                        ScoreUpdateRepository scoreUpdateRepository,
                        TeamService teamService) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.playerMatchStatRepository = playerMatchStatRepository;
        this.scoreUpdateRepository = scoreUpdateRepository;
        this.teamService = teamService;
    }

    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MatchDTO> getLiveMatches() {
        return matchRepository.findAllLiveMatches().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MatchDTO> getCompletedMatches() {
        return matchRepository.findRecentCompletedMatches().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MatchDTO> getScheduledMatches() {
        return matchRepository.findByStatus(MatchStatus.SCHEDULED).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MatchDTO getMatchById(Long id) {
        Match match = findMatch(id);
        return toDTO(match);
    }

    @Transactional
    public MatchDTO createMatch(CreateMatchRequest req) {
        Team t1 = teamRepository.findById(req.getTeam1Id())
                .orElseThrow(() -> new RuntimeException("Team1 not found: " + req.getTeam1Id()));
        Team t2 = teamRepository.findById(req.getTeam2Id())
                .orElseThrow(() -> new RuntimeException("Team2 not found: " + req.getTeam2Id()));
        if (t1.getId().equals(t2.getId())) throw new RuntimeException("A team cannot play against itself");

        int maxOvers = switch (req.getFormat()) {
            case T20 -> 20;
            case T10 -> 10;
            case ODI -> 50;
            case TEST -> 0;
        };

        Match match = Match.builder()
                .title(req.getTitle())
                .team1(t1)
                .team2(t2)
                .format(req.getFormat())
                .status(MatchStatus.SCHEDULED)
                .venue(req.getVenue())
                .scheduledAt(req.getScheduledAt() != null ? req.getScheduledAt() : LocalDateTime.now())
                .tossWinner(req.getTossWinner())
                .tossDecision(req.getTossDecision())
                .maxOvers(maxOvers)
                .isLive(false)
                .currentInnings(1)
                .build();

        match = matchRepository.save(match);
        log.info("Created match: {} (id={})", match.getTitle(), match.getId());
        return toDTO(match);
    }

    @Transactional
    public MatchDTO startMatch(Long matchId) {
        Match match = findMatch(matchId);
        if (match.getStatus() != MatchStatus.SCHEDULED) {
            throw new RuntimeException("Match cannot be started: current status = " + match.getStatus());
        }

        String battingTeam, bowlingTeam;
        if (match.getTossWinner() != null && match.getTossDecision() != null) {
            if ("BAT".equalsIgnoreCase(match.getTossDecision())) {
                battingTeam = match.getTossWinner();
                bowlingTeam = (match.getTossWinner().equals(match.getTeam1().getShortName()))
                        ? match.getTeam2().getShortName() : match.getTeam1().getShortName();
            } else {
                bowlingTeam = match.getTossWinner();
                battingTeam = (match.getTossWinner().equals(match.getTeam1().getShortName()))
                        ? match.getTeam2().getShortName() : match.getTeam1().getShortName();
            }
        } else {
            battingTeam = match.getTeam1().getShortName();
            bowlingTeam = match.getTeam2().getShortName();
        }

        match.setStatus(MatchStatus.LIVE);
        match.setIsLive(true);
        match.setStartedAt(LocalDateTime.now());
        match.setBattingTeam(battingTeam);
        match.setBowlingTeam(bowlingTeam);
        match.setCurrentInnings(1);

        match = matchRepository.save(match);
        log.info("Match started: {} | Batting: {}", match.getTitle(), battingTeam);
        return toDTO(match);
    }

    @Transactional
    public MatchDTO postScoreUpdate(ScoreUpdateRequest req) {
        Match match = findMatch(req.getMatchId());
        if (match.getStatus() != MatchStatus.LIVE) {
            throw new RuntimeException("Match is not LIVE. Current status: " + match.getStatus());
        }

        Player batsman = playerRepository.findById(req.getBatsmanId())
                .orElseThrow(() -> new RuntimeException("Batsman not found: " + req.getBatsmanId()));
        Player bowler = playerRepository.findById(req.getBowlerId())
                .orElseThrow(() -> new RuntimeException("Bowler not found: " + req.getBowlerId()));

        Player fielder = null;
        if (req.getFielderId() != null) {
            fielder = playerRepository.findById(req.getFielderId()).orElse(null);
        }

        boolean isWide = Boolean.TRUE.equals(req.getIsWide());
        boolean isNoBall = Boolean.TRUE.equals(req.getIsNoBall());
        boolean isWicket = Boolean.TRUE.equals(req.getIsWicket());
        boolean isFour = !isWide && req.getRunsScored() == 4;
        boolean isSix = !isWide && req.getRunsScored() == 6;
        int runs = req.getRunsScored();

        int innings = match.getCurrentInnings();

        int overs, balls;
        if (innings == 1) {
            if (match.getBattingTeam().equals(match.getTeam1().getShortName())) {
                overs = match.getTeam1Inn1Overs();
                balls = match.getTeam1Inn1Balls();
            } else {
                overs = match.getTeam2Inn1Overs();
                balls = match.getTeam2Inn1Balls();
            }
        } else {
            overs = 0; balls = 0;
        }

        String commentary = buildCommentary(batsman.getName(), bowler.getName(),
                runs, isWicket, req.getWicketType(), isWide, isNoBall, isFour, isSix,
                fielder != null ? fielder.getName() : null);

        int currentTotalRuns = getCurrentRuns(match);
        int currentTotalWickets = getCurrentWickets(match);

        ScoreUpdate update = ScoreUpdate.builder()
                .match(match)
                .inningsNumber(innings)
                .overNumber(overs)
                .ballNumber(balls + 1)
                .runsScored(runs)
                .isWicket(isWicket)
                .wicketType(req.getWicketType())
                .isWide(isWide)
                .isNoBall(isNoBall)
                .isFour(isFour)
                .isSix(isSix)
                .batsman(batsman.getName())
                .bowler(bowler.getName())
                .fielder(fielder != null ? fielder.getName() : null)
                .commentary(commentary)
                .totalRuns(currentTotalRuns + runs)
                .totalWickets(currentTotalWickets + (isWicket ? 1 : 0))
                .timestamp(LocalDateTime.now())
                .build();
        scoreUpdateRepository.save(update);

        updateMatchScore(match, runs, isWicket, isWide, isNoBall);

        updatePlayerStats(match, batsman, bowler, fielder, runs, isWicket,
                req.getWicketType(), isWide, isNoBall, isFour, isSix, innings);

        match.setLastBallCommentary(commentary);

        match = checkInningsOrMatchOver(match);
        match = matchRepository.save(match);

        return toDTO(match);
    }

    @Transactional
    public MatchDTO completeMatch(Long matchId, String result) {
        Match match = findMatch(matchId);
        match.setStatus(MatchStatus.COMPLETED);
        match.setIsLive(false);
        match.setCompletedAt(LocalDateTime.now());
        match.setResult(result);
        match = matchRepository.save(match);

        updateTeamRecords(match);
        log.info("Match completed: {} | Result: {}", match.getTitle(), result);
        return toDTO(match);
    }

    private void updateMatchScore(Match match, int runs, boolean isWicket, boolean isWide, boolean isNoBall) {
        int innings = match.getCurrentInnings();
        boolean batting1st = match.getBattingTeam().equals(match.getTeam1().getShortName());

        if (innings == 1) {
            if (batting1st) {
                match.setTeam1Inn1Runs(match.getTeam1Inn1Runs() + runs);
                if (isWicket) match.setTeam1Inn1Wickets(match.getTeam1Inn1Wickets() + 1);
                if (!isWide && !isNoBall) {
                    int b = match.getTeam1Inn1Balls() + 1;
                    match.setTeam1Inn1Balls(b % 6);
                    if (b % 6 == 0) match.setTeam1Inn1Overs(match.getTeam1Inn1Overs() + 1);
                }
            } else {
                match.setTeam2Inn1Runs(match.getTeam2Inn1Runs() + runs);
                if (isWicket) match.setTeam2Inn1Wickets(match.getTeam2Inn1Wickets() + 1);
                if (!isWide && !isNoBall) {
                    int b = match.getTeam2Inn1Balls() + 1;
                    match.setTeam2Inn1Balls(b % 6);
                    if (b % 6 == 0) match.setTeam2Inn1Overs(match.getTeam2Inn1Overs() + 1);
                }
            }
        }
    }

    private void updatePlayerStats(Match match, Player batsman, Player bowler, Player fielder,
                                   int runs, boolean isWicket, String wicketType,
                                   boolean isWide, boolean isNoBall, boolean isFour, boolean isSix, int innings) {
        PlayerMatchStat batStats = playerMatchStatRepository
                .findByMatchIdAndPlayerIdAndInningsNumber(match.getId(), batsman.getId(), innings)
                .orElseGet(() -> PlayerMatchStat.builder()
                        .match(match).player(batsman).inningsNumber(innings)
                        .isBatting(true).isOut(false).build());

        if (!isWide) {
            batStats.setRunsScored(batStats.getRunsScored() + runs);
            batStats.setBallsFaced(batStats.getBallsFaced() + 1);
            if (isFour) batStats.setFours(batStats.getFours() + 1);
            if (isSix) batStats.setSixes(batStats.getSixes() + 1);
        }
        if (isWicket && !"Run Out".equals(wicketType)) {
            batStats.setIsOut(true);
            batStats.setDismissalType(wicketType);
            batStats.setDismissedBy(bowler.getName());
            batStats.setIsBatting(false);
        }
        playerMatchStatRepository.save(batStats);

        PlayerMatchStat bowlStats = playerMatchStatRepository
                .findByMatchIdAndPlayerIdAndInningsNumber(match.getId(), bowler.getId(), innings)
                .orElseGet(() -> PlayerMatchStat.builder()
                        .match(match).player(bowler).inningsNumber(innings)
                        .isBowling(true).build());

        bowlStats.setRunsConceded(bowlStats.getRunsConceded() + runs);
        if (isWide) bowlStats.setWides(bowlStats.getWides() + 1);
        else if (isNoBall) bowlStats.setNoBalls(bowlStats.getNoBalls() + 1);
        else {
            int b = bowlStats.getBallsBowled() + 1;
            bowlStats.setBallsBowled(b % 6);
            if (b % 6 == 0) {
                bowlStats.setOversBowled(bowlStats.getOversBowled() + 1);
                bowlStats.setBallsBowled(0);
            }
        }
        if (isWicket && !"Run Out".equals(wicketType)) {
            bowlStats.setWicketsTaken(bowlStats.getWicketsTaken() + 1);
        }
        playerMatchStatRepository.save(bowlStats);

        if (fielder != null && isWicket) {
            PlayerMatchStat fieldStats = playerMatchStatRepository
                    .findByMatchIdAndPlayerIdAndInningsNumber(match.getId(), fielder.getId(), innings)
                    .orElseGet(() -> PlayerMatchStat.builder()
                            .match(match).player(fielder).inningsNumber(innings).build());
            if ("Caught".equals(wicketType)) fieldStats.setCatches(fieldStats.getCatches() + 1);
            else if ("Run Out".equals(wicketType)) fieldStats.setRunOuts(fieldStats.getRunOuts() + 1);
            else if ("Stumped".equals(wicketType)) fieldStats.setStumpings(fieldStats.getStumpings() + 1);
            playerMatchStatRepository.save(fieldStats);
        }
    }

    private Match checkInningsOrMatchOver(Match match) {
        int maxOvers = match.getMaxOvers();
        int innings = match.getCurrentInnings();
        boolean batting1st = match.getBattingTeam().equals(match.getTeam1().getShortName());

        int overs, wickets;
        if (innings == 1) {
            if (batting1st) {
                overs = match.getTeam1Inn1Overs();
                wickets = match.getTeam1Inn1Wickets();
            } else {
                overs = match.getTeam2Inn1Overs();
                wickets = match.getTeam2Inn1Wickets();
            }
        } else {
            return match;
        }

        boolean inningsOver = (maxOvers > 0 && overs >= maxOvers) || wickets >= 10;
        if (inningsOver && innings == 1) {
            String prevBatting = match.getBattingTeam();
            String prevBowling = match.getBowlingTeam();
            match.setBattingTeam(prevBowling);
            match.setBowlingTeam(prevBatting);
            match.setCurrentInnings(2);
            match.setStatus(MatchStatus.INNINGS_BREAK);
            match.setIsLive(false);

            if (maxOvers > 0) {
                int inn1Runs = batting1st ? match.getTeam1Inn1Runs() : match.getTeam2Inn1Runs();
                match.setTargetRuns(inn1Runs + 1);
            }
            log.info("Innings 1 over for match: {}. Target: {}", match.getTitle(), match.getTargetRuns());
        }
        return match;
    }

    @Transactional
    public MatchDTO startSecondInnings(Long matchId) {
        Match match = findMatch(matchId);
        if (match.getStatus() != MatchStatus.INNINGS_BREAK) {
            throw new RuntimeException("Match is not in INNINGS_BREAK status");
        }
        match.setStatus(MatchStatus.LIVE);
        match.setIsLive(true);
        match = matchRepository.save(match);
        log.info("Second innings started: {}", match.getTitle());
        return toDTO(match);
    }

    private String buildCommentary(String batsman, String bowler, int runs,
                                   boolean isWicket, String wicketType,
                                   boolean isWide, boolean isNoBall,
                                   boolean isFour, boolean isSix, String fielder) {
        if (isWicket) {
            String base = wicketType + "! " + batsman + " is out";
            if ("Caught".equals(wicketType) && fielder != null)
                return base + " caught by " + fielder + ", bowled by " + bowler + ".";
            if ("Bowled".equals(wicketType))
                return "Bowled! " + bowler + " gets " + batsman + " with a beautiful delivery!";
            if ("LBW".equals(wicketType))
                return "LBW! " + batsman + " is plumb in front, given out!";
            if ("Run Out".equals(wicketType))
                return "RUN OUT! " + batsman + " is caught short of the crease!";
            if ("Stumped".equals(wicketType))
                return "Stumped! " + batsman + " is out of his crease, " + bowler + " strikes!";
            return base + ", bowled by " + bowler + ".";
        }
        if (isSix) return "SIX! " + batsman + " smashes " + bowler + " over the boundary!";
        if (isFour) return "FOUR! " + batsman + " drives " + bowler + " to the boundary!";
        if (isWide) return "Wide ball by " + bowler + ". Extra run added.";
        if (isNoBall) return "No Ball! " + bowler + ". Free hit coming up!";
        if (runs == 0) return "Dot ball. " + bowler + " keeps it tight against " + batsman + ".";
        if (runs == 1) return runs + " run. " + batsman + " rotates the strike.";
        if (runs == 2) return runs + " runs. " + batsman + " places it well.";
        if (runs == 3) return runs + " runs! Good running between the wickets.";
        return runs + " off " + bowler + " to " + batsman + ".";
    }

    private int getCurrentRuns(Match match) {
        if (match.getCurrentInnings() == 1 && match.getBattingTeam() != null) {
            return match.getBattingTeam().equals(match.getTeam1().getShortName())
                    ? match.getTeam1Inn1Runs() : match.getTeam2Inn1Runs();
        }
        return 0;
    }

    private int getCurrentWickets(Match match) {
        if (match.getCurrentInnings() == 1 && match.getBattingTeam() != null) {
            return match.getBattingTeam().equals(match.getTeam1().getShortName())
                    ? match.getTeam1Inn1Wickets() : match.getTeam2Inn1Wickets();
        }
        return 0;
    }

    private void updateTeamRecords(Match match) {
        Team t1 = match.getTeam1();
        Team t2 = match.getTeam2();
        t1.setMatchesPlayed(t1.getMatchesPlayed() + 1);
        t2.setMatchesPlayed(t2.getMatchesPlayed() + 1);
        if (match.getWinnerTeamName() != null) {
            if (match.getWinnerTeamName().equals(t1.getShortName())) {
                t1.setWins(t1.getWins() + 1);
                t2.setLosses(t2.getLosses() + 1);
            } else {
                t2.setWins(t2.getWins() + 1);
                t1.setLosses(t1.getLosses() + 1);
            }
        } else {
            t1.setDraws(t1.getDraws() + 1);
            t2.setDraws(t2.getDraws() + 1);
        }
        teamRepository.save(t1);
        teamRepository.save(t2);
    }

    public List<ScoreUpdateDTO> getCommentary(Long matchId) {
        return scoreUpdateRepository.findByMatchIdOrderByTimestampDesc(matchId)
                .stream().map(this::toScoreUpdateDTO).collect(Collectors.toList());
    }

    public List<ScoreUpdateDTO> getRecentCommentary(Long matchId) {
        return scoreUpdateRepository.findLast20Updates(matchId)
                .stream().map(this::toScoreUpdateDTO).collect(Collectors.toList());
    }

    public List<PlayerMatchStatDTO> getScorecard(Long matchId, Integer innings) {
        return playerMatchStatRepository.findByMatchIdAndInningsNumber(matchId, innings)
                .stream().map(this::toStatDTO).collect(Collectors.toList());
    }

    private Match findMatch(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found: " + id));
    }

    public MatchDTO toDTO(Match match) {
        List<PlayerMatchStatDTO> currentBatsmen = playerMatchStatRepository
                .findCurrentBatsmen(match.getId())
                .stream().map(this::toStatDTO).collect(Collectors.toList());

        Optional<PlayerMatchStat> bowlerOpt = playerMatchStatRepository
                .findCurrentBowler(match.getId()).stream().findFirst();

        List<ScoreUpdateDTO> recentUpdates = scoreUpdateRepository
                .findLast20Updates(match.getId())
                .stream().map(this::toScoreUpdateDTO).collect(Collectors.toList());

        double crr = 0.0, rrr = 0.0;
        int runsRequired = 0, ballsRemaining = 0;
        int runs = getCurrentRuns(match);
        int curOvers = 0, curBalls = 0;

        if (match.getCurrentInnings() == 1 && match.getBattingTeam() != null) {
            if (match.getBattingTeam().equals(match.getTeam1().getShortName())) {
                curOvers = match.getTeam1Inn1Overs();
                curBalls = match.getTeam1Inn1Balls();
            } else {
                curOvers = match.getTeam2Inn1Overs();
                curBalls = match.getTeam2Inn1Balls();
            }
        }
        double totalOversFaced = curOvers + (curBalls / 6.0);
        if (totalOversFaced > 0) crr = Math.round((runs / totalOversFaced) * 100.0) / 100.0;

        if (match.getTargetRuns() > 0 && match.getCurrentInnings() == 2 && match.getMaxOvers() > 0) {
            runsRequired = match.getTargetRuns() - runs;
            int oversLeft = match.getMaxOvers() - curOvers;
            ballsRemaining = oversLeft * 6 - curBalls;
            if (ballsRemaining > 0) rrr = Math.round((runsRequired / (ballsRemaining / 6.0)) * 100.0) / 100.0;
        }

        return MatchDTO.builder()
                .id(match.getId())
                .title(match.getTitle())
                .team1(teamService.toDTO(match.getTeam1()))
                .team2(teamService.toDTO(match.getTeam2()))
                .format(match.getFormat())
                .status(match.getStatus())
                .venue(match.getVenue())
                .scheduledAt(match.getScheduledAt())
                .startedAt(match.getStartedAt())
                .completedAt(match.getCompletedAt())
                .tossWinner(match.getTossWinner())
                .tossDecision(match.getTossDecision())
                .currentInnings(match.getCurrentInnings())
                .battingTeam(match.getBattingTeam())
                .bowlingTeam(match.getBowlingTeam())
                .team1Inn1Runs(match.getTeam1Inn1Runs())
                .team1Inn1Wickets(match.getTeam1Inn1Wickets())
                .team1Inn1Overs(match.getTeam1Inn1Overs())
                .team1Inn1Balls(match.getTeam1Inn1Balls())
                .team2Inn1Runs(match.getTeam2Inn1Runs())
                .team2Inn1Wickets(match.getTeam2Inn1Wickets())
                .team2Inn1Overs(match.getTeam2Inn1Overs())
                .team2Inn1Balls(match.getTeam2Inn1Balls())
                .team1Inn2Runs(match.getTeam1Inn2Runs())
                .team1Inn2Wickets(match.getTeam1Inn2Wickets())
                .team2Inn2Runs(match.getTeam2Inn2Runs())
                .team2Inn2Wickets(match.getTeam2Inn2Wickets())
                .result(match.getResult())
                .winnerTeamName(match.getWinnerTeamName())
                .maxOvers(match.getMaxOvers())
                .targetRuns(match.getTargetRuns())
                .isLive(match.getIsLive())
                .lastBallCommentary(match.getLastBallCommentary())
                .currentRunRate(crr)
                .requiredRunRate(rrr)
                .runsRequired(runsRequired)
                .ballsRemaining(ballsRemaining)
                .currentBatsmen(currentBatsmen)
                .currentBowler(bowlerOpt.map(this::toStatDTO).orElse(null))
                .recentUpdates(recentUpdates)
                .build();
    }

    public PlayerMatchStatDTO toStatDTO(PlayerMatchStat s) {
        int bf = s.getBallsFaced() != null ? s.getBallsFaced() : 1;
        double sr = bf > 0 ? Math.round(((double) s.getRunsScored() / bf) * 100.0 * 100.0) / 100.0 : 0.0;
        double eco = s.getOversBowled() != null && s.getOversBowled() > 0
                ? Math.round(((double) s.getRunsConceded() / s.getOversBowled()) * 100.0) / 100.0 : 0.0;

        return PlayerMatchStatDTO.builder()
                .id(s.getId())
                .playerId(s.getPlayer().getId())
                .playerName(s.getPlayer().getName())
                .role(s.getPlayer().getRole())
                .inningsNumber(s.getInningsNumber())
                .runsScored(s.getRunsScored())
                .ballsFaced(s.getBallsFaced())
                .fours(s.getFours())
                .sixes(s.getSixes())
                .isOut(s.getIsOut())
                .dismissalType(s.getDismissalType())
                .dismissedBy(s.getDismissedBy())
                .isBatting(s.getIsBatting())
                .battingPosition(s.getBattingPosition())
                .strikeRate(sr)
                .oversBowled(s.getOversBowled())
                .ballsBowled(s.getBallsBowled())
                .runsConceded(s.getRunsConceded())
                .wicketsTaken(s.getWicketsTaken())
                .maidens(s.getMaidens())
                .noBalls(s.getNoBalls())
                .wides(s.getWides())
                .isBowling(s.getIsBowling())
                .economy(eco)
                .catches(s.getCatches())
                .runOuts(s.getRunOuts())
                .stumpings(s.getStumpings())
                .build();
    }

    public ScoreUpdateDTO toScoreUpdateDTO(ScoreUpdate u) {
        return ScoreUpdateDTO.builder()
                .id(u.getId())
                .matchId(u.getMatch().getId())
                .inningsNumber(u.getInningsNumber())
                .overNumber(u.getOverNumber())
                .ballNumber(u.getBallNumber())
                .runsScored(u.getRunsScored())
                .isWicket(u.getIsWicket())
                .wicketType(u.getWicketType())
                .isWide(u.getIsWide())
                .isNoBall(u.getIsNoBall())
                .isFour(u.getIsFour())
                .isSix(u.getIsSix())
                .batsman(u.getBatsman())
                .bowler(u.getBowler())
                .fielder(u.getFielder())
                .commentary(u.getCommentary())
                .totalRuns(u.getTotalRuns())
                .totalWickets(u.getTotalWickets())
                .timestamp(u.getTimestamp())
                .build();
    }
}
