package com.cricket.dto;

import com.cricket.model.MatchFormat;
import com.cricket.model.MatchStatus;
import java.time.LocalDateTime;
import java.util.List;

public class MatchDTO {
    private Long id;
    private String title;
    private TeamDTO team1;
    private TeamDTO team2;
    private MatchFormat format;
    private MatchStatus status;
    private String venue;
    private LocalDateTime scheduledAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    private String tossWinner;
    private String tossDecision;

    private Integer currentInnings;
    private String battingTeam;
    private String bowlingTeam;

    private Integer team1Inn1Runs;
    private Integer team1Inn1Wickets;
    private Integer team1Inn1Overs;
    private Integer team1Inn1Balls;

    private Integer team2Inn1Runs;
    private Integer team2Inn1Wickets;
    private Integer team2Inn1Overs;
    private Integer team2Inn1Balls;

    private Integer team1Inn2Runs;
    private Integer team1Inn2Wickets;
    private Integer team2Inn2Runs;
    private Integer team2Inn2Wickets;

    private String result;
    private String winnerTeamName;

    private Integer maxOvers;
    private Integer targetRuns;
    private Boolean isLive;
    private String lastBallCommentary;

    private Double currentRunRate;
    private Double requiredRunRate;
    private Integer runsRequired;
    private Integer ballsRemaining;

    private List<PlayerMatchStatDTO> currentBatsmen;
    private PlayerMatchStatDTO currentBowler;

    private List<PlayerMatchStatDTO> innings1BattingCard;
    private List<PlayerMatchStatDTO> innings1BowlingCard;
    private List<PlayerMatchStatDTO> innings2BattingCard;
    private List<PlayerMatchStatDTO> innings2BowlingCard;

    private List<ScoreUpdateDTO> recentUpdates;

    public MatchDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public TeamDTO getTeam1() { return team1; }
    public void setTeam1(TeamDTO team1) { this.team1 = team1; }
    public TeamDTO getTeam2() { return team2; }
    public void setTeam2(TeamDTO team2) { this.team2 = team2; }
    public MatchFormat getFormat() { return format; }
    public void setFormat(MatchFormat format) { this.format = format; }
    public MatchStatus getStatus() { return status; }
    public void setStatus(MatchStatus status) { this.status = status; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public String getTossWinner() { return tossWinner; }
    public void setTossWinner(String tossWinner) { this.tossWinner = tossWinner; }
    public String getTossDecision() { return tossDecision; }
    public void setTossDecision(String tossDecision) { this.tossDecision = tossDecision; }
    public Integer getCurrentInnings() { return currentInnings; }
    public void setCurrentInnings(Integer currentInnings) { this.currentInnings = currentInnings; }
    public String getBattingTeam() { return battingTeam; }
    public void setBattingTeam(String battingTeam) { this.battingTeam = battingTeam; }
    public String getBowlingTeam() { return bowlingTeam; }
    public void setBowlingTeam(String bowlingTeam) { this.bowlingTeam = bowlingTeam; }
    public Integer getTeam1Inn1Runs() { return team1Inn1Runs; }
    public void setTeam1Inn1Runs(Integer team1Inn1Runs) { this.team1Inn1Runs = team1Inn1Runs; }
    public Integer getTeam1Inn1Wickets() { return team1Inn1Wickets; }
    public void setTeam1Inn1Wickets(Integer team1Inn1Wickets) { this.team1Inn1Wickets = team1Inn1Wickets; }
    public Integer getTeam1Inn1Overs() { return team1Inn1Overs; }
    public void setTeam1Inn1Overs(Integer team1Inn1Overs) { this.team1Inn1Overs = team1Inn1Overs; }
    public Integer getTeam1Inn1Balls() { return team1Inn1Balls; }
    public void setTeam1Inn1Balls(Integer team1Inn1Balls) { this.team1Inn1Balls = team1Inn1Balls; }
    public Integer getTeam2Inn1Runs() { return team2Inn1Runs; }
    public void setTeam2Inn1Runs(Integer team2Inn1Runs) { this.team2Inn1Runs = team2Inn1Runs; }
    public Integer getTeam2Inn1Wickets() { return team2Inn1Wickets; }
    public void setTeam2Inn1Wickets(Integer team2Inn1Wickets) { this.team2Inn1Wickets = team2Inn1Wickets; }
    public Integer getTeam2Inn1Overs() { return team2Inn1Overs; }
    public void setTeam2Inn1Overs(Integer team2Inn1Overs) { this.team2Inn1Overs = team2Inn1Overs; }
    public Integer getTeam2Inn1Balls() { return team2Inn1Balls; }
    public void setTeam2Inn1Balls(Integer team2Inn1Balls) { this.team2Inn1Balls = team2Inn1Balls; }
    public Integer getTeam1Inn2Runs() { return team1Inn2Runs; }
    public void setTeam1Inn2Runs(Integer team1Inn2Runs) { this.team1Inn2Runs = team1Inn2Runs; }
    public Integer getTeam1Inn2Wickets() { return team1Inn2Wickets; }
    public void setTeam1Inn2Wickets(Integer team1Inn2Wickets) { this.team1Inn2Wickets = team1Inn2Wickets; }
    public Integer getTeam2Inn2Runs() { return team2Inn2Runs; }
    public void setTeam2Inn2Runs(Integer team2Inn2Runs) { this.team2Inn2Runs = team2Inn2Runs; }
    public Integer getTeam2Inn2Wickets() { return team2Inn2Wickets; }
    public void setTeam2Inn2Wickets(Integer team2Inn2Wickets) { this.team2Inn2Wickets = team2Inn2Wickets; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getWinnerTeamName() { return winnerTeamName; }
    public void setWinnerTeamName(String winnerTeamName) { this.winnerTeamName = winnerTeamName; }
    public Integer getMaxOvers() { return maxOvers; }
    public void setMaxOvers(Integer maxOvers) { this.maxOvers = maxOvers; }
    public Integer getTargetRuns() { return targetRuns; }
    public void setTargetRuns(Integer targetRuns) { this.targetRuns = targetRuns; }
    public Boolean getIsLive() { return isLive; }
    public void setIsLive(Boolean isLive) { this.isLive = isLive; }
    public String getLastBallCommentary() { return lastBallCommentary; }
    public void setLastBallCommentary(String lastBallCommentary) { this.lastBallCommentary = lastBallCommentary; }
    public Double getCurrentRunRate() { return currentRunRate; }
    public void setCurrentRunRate(Double currentRunRate) { this.currentRunRate = currentRunRate; }
    public Double getRequiredRunRate() { return requiredRunRate; }
    public void setRequiredRunRate(Double requiredRunRate) { this.requiredRunRate = requiredRunRate; }
    public Integer getRunsRequired() { return runsRequired; }
    public void setRunsRequired(Integer runsRequired) { this.runsRequired = runsRequired; }
    public Integer getBallsRemaining() { return ballsRemaining; }
    public void setBallsRemaining(Integer ballsRemaining) { this.ballsRemaining = ballsRemaining; }
    public List<PlayerMatchStatDTO> getCurrentBatsmen() { return currentBatsmen; }
    public void setCurrentBatsmen(List<PlayerMatchStatDTO> currentBatsmen) { this.currentBatsmen = currentBatsmen; }
    public PlayerMatchStatDTO getCurrentBowler() { return currentBowler; }
    public void setCurrentBowler(PlayerMatchStatDTO currentBowler) { this.currentBowler = currentBowler; }
    public List<PlayerMatchStatDTO> getInnings1BattingCard() { return innings1BattingCard; }
    public void setInnings1BattingCard(List<PlayerMatchStatDTO> innings1BattingCard) { this.innings1BattingCard = innings1BattingCard; }
    public List<PlayerMatchStatDTO> getInnings1BowlingCard() { return innings1BowlingCard; }
    public void setInnings1BowlingCard(List<PlayerMatchStatDTO> innings1BowlingCard) { this.innings1BowlingCard = innings1BowlingCard; }
    public List<PlayerMatchStatDTO> getInnings2BattingCard() { return innings2BattingCard; }
    public void setInnings2BattingCard(List<PlayerMatchStatDTO> innings2BattingCard) { this.innings2BattingCard = innings2BattingCard; }
    public List<PlayerMatchStatDTO> getInnings2BowlingCard() { return innings2BowlingCard; }
    public void setInnings2BowlingCard(List<PlayerMatchStatDTO> innings2BowlingCard) { this.innings2BowlingCard = innings2BowlingCard; }
    public List<ScoreUpdateDTO> getRecentUpdates() { return recentUpdates; }
    public void setRecentUpdates(List<ScoreUpdateDTO> recentUpdates) { this.recentUpdates = recentUpdates; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final MatchDTO m = new MatchDTO();
        public Builder id(Long v) { m.id = v; return this; }
        public Builder title(String v) { m.title = v; return this; }
        public Builder team1(TeamDTO v) { m.team1 = v; return this; }
        public Builder team2(TeamDTO v) { m.team2 = v; return this; }
        public Builder format(MatchFormat v) { m.format = v; return this; }
        public Builder status(MatchStatus v) { m.status = v; return this; }
        public Builder venue(String v) { m.venue = v; return this; }
        public Builder scheduledAt(LocalDateTime v) { m.scheduledAt = v; return this; }
        public Builder startedAt(LocalDateTime v) { m.startedAt = v; return this; }
        public Builder completedAt(LocalDateTime v) { m.completedAt = v; return this; }
        public Builder tossWinner(String v) { m.tossWinner = v; return this; }
        public Builder tossDecision(String v) { m.tossDecision = v; return this; }
        public Builder currentInnings(Integer v) { m.currentInnings = v; return this; }
        public Builder battingTeam(String v) { m.battingTeam = v; return this; }
        public Builder bowlingTeam(String v) { m.bowlingTeam = v; return this; }
        public Builder team1Inn1Runs(Integer v) { m.team1Inn1Runs = v; return this; }
        public Builder team1Inn1Wickets(Integer v) { m.team1Inn1Wickets = v; return this; }
        public Builder team1Inn1Overs(Integer v) { m.team1Inn1Overs = v; return this; }
        public Builder team1Inn1Balls(Integer v) { m.team1Inn1Balls = v; return this; }
        public Builder team2Inn1Runs(Integer v) { m.team2Inn1Runs = v; return this; }
        public Builder team2Inn1Wickets(Integer v) { m.team2Inn1Wickets = v; return this; }
        public Builder team2Inn1Overs(Integer v) { m.team2Inn1Overs = v; return this; }
        public Builder team2Inn1Balls(Integer v) { m.team2Inn1Balls = v; return this; }
        public Builder team1Inn2Runs(Integer v) { m.team1Inn2Runs = v; return this; }
        public Builder team1Inn2Wickets(Integer v) { m.team1Inn2Wickets = v; return this; }
        public Builder team2Inn2Runs(Integer v) { m.team2Inn2Runs = v; return this; }
        public Builder team2Inn2Wickets(Integer v) { m.team2Inn2Wickets = v; return this; }
        public Builder result(String v) { m.result = v; return this; }
        public Builder winnerTeamName(String v) { m.winnerTeamName = v; return this; }
        public Builder maxOvers(Integer v) { m.maxOvers = v; return this; }
        public Builder targetRuns(Integer v) { m.targetRuns = v; return this; }
        public Builder isLive(Boolean v) { m.isLive = v; return this; }
        public Builder lastBallCommentary(String v) { m.lastBallCommentary = v; return this; }
        public Builder currentRunRate(Double v) { m.currentRunRate = v; return this; }
        public Builder requiredRunRate(Double v) { m.requiredRunRate = v; return this; }
        public Builder runsRequired(Integer v) { m.runsRequired = v; return this; }
        public Builder ballsRemaining(Integer v) { m.ballsRemaining = v; return this; }
        public Builder currentBatsmen(List<PlayerMatchStatDTO> v) { m.currentBatsmen = v; return this; }
        public Builder currentBowler(PlayerMatchStatDTO v) { m.currentBowler = v; return this; }
        public Builder innings1BattingCard(List<PlayerMatchStatDTO> v) { m.innings1BattingCard = v; return this; }
        public Builder innings1BowlingCard(List<PlayerMatchStatDTO> v) { m.innings1BowlingCard = v; return this; }
        public Builder innings2BattingCard(List<PlayerMatchStatDTO> v) { m.innings2BattingCard = v; return this; }
        public Builder innings2BowlingCard(List<PlayerMatchStatDTO> v) { m.innings2BowlingCard = v; return this; }
        public Builder recentUpdates(List<ScoreUpdateDTO> v) { m.recentUpdates = v; return this; }
        public MatchDTO build() { return m; }
    }
}
