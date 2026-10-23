package com.cricket.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a cricket match.
 */
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchFormat format;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status = MatchStatus.SCHEDULED;

    @Column(nullable = false)
    private String venue;

    private LocalDateTime scheduledAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    private String tossWinner;
    private String tossDecision;

    private Integer currentInnings = 1;

    private Integer team1Inn1Runs = 0;
    private Integer team1Inn1Wickets = 0;
    private Integer team1Inn1Overs = 0;
    private Integer team1Inn1Balls = 0;

    private Integer team2Inn1Runs = 0;
    private Integer team2Inn1Wickets = 0;
    private Integer team2Inn1Overs = 0;
    private Integer team2Inn1Balls = 0;

    private Integer team1Inn2Runs = 0;
    private Integer team1Inn2Wickets = 0;
    private Integer team2Inn2Runs = 0;
    private Integer team2Inn2Wickets = 0;

    private String battingTeam;
    private String bowlingTeam;
    private String result;
    private String winnerTeamName;

    private Integer maxOvers = 50;
    private Integer targetRuns = 0;
    private Boolean isLive = false;

    @Column(length = 500)
    private String lastBallCommentary;

    public Match() {}

    // ── Getters / Setters ───────────────────────────────────────
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Team getTeam1() { return team1; }
    public void setTeam1(Team team1) { this.team1 = team1; }
    public Team getTeam2() { return team2; }
    public void setTeam2(Team team2) { this.team2 = team2; }
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
    public Integer getTeam1Inn1Runs() { return team1Inn1Runs; }
    public void setTeam1Inn1Runs(Integer v) { this.team1Inn1Runs = v; }
    public Integer getTeam1Inn1Wickets() { return team1Inn1Wickets; }
    public void setTeam1Inn1Wickets(Integer v) { this.team1Inn1Wickets = v; }
    public Integer getTeam1Inn1Overs() { return team1Inn1Overs; }
    public void setTeam1Inn1Overs(Integer v) { this.team1Inn1Overs = v; }
    public Integer getTeam1Inn1Balls() { return team1Inn1Balls; }
    public void setTeam1Inn1Balls(Integer v) { this.team1Inn1Balls = v; }
    public Integer getTeam2Inn1Runs() { return team2Inn1Runs; }
    public void setTeam2Inn1Runs(Integer v) { this.team2Inn1Runs = v; }
    public Integer getTeam2Inn1Wickets() { return team2Inn1Wickets; }
    public void setTeam2Inn1Wickets(Integer v) { this.team2Inn1Wickets = v; }
    public Integer getTeam2Inn1Overs() { return team2Inn1Overs; }
    public void setTeam2Inn1Overs(Integer v) { this.team2Inn1Overs = v; }
    public Integer getTeam2Inn1Balls() { return team2Inn1Balls; }
    public void setTeam2Inn1Balls(Integer v) { this.team2Inn1Balls = v; }
    public Integer getTeam1Inn2Runs() { return team1Inn2Runs; }
    public void setTeam1Inn2Runs(Integer v) { this.team1Inn2Runs = v; }
    public Integer getTeam1Inn2Wickets() { return team1Inn2Wickets; }
    public void setTeam1Inn2Wickets(Integer v) { this.team1Inn2Wickets = v; }
    public Integer getTeam2Inn2Runs() { return team2Inn2Runs; }
    public void setTeam2Inn2Runs(Integer v) { this.team2Inn2Runs = v; }
    public Integer getTeam2Inn2Wickets() { return team2Inn2Wickets; }
    public void setTeam2Inn2Wickets(Integer v) { this.team2Inn2Wickets = v; }
    public String getBattingTeam() { return battingTeam; }
    public void setBattingTeam(String battingTeam) { this.battingTeam = battingTeam; }
    public String getBowlingTeam() { return bowlingTeam; }
    public void setBowlingTeam(String bowlingTeam) { this.bowlingTeam = bowlingTeam; }
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

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Match m = new Match();
        public Builder title(String v) { m.title = v; return this; }
        public Builder team1(Team v) { m.team1 = v; return this; }
        public Builder team2(Team v) { m.team2 = v; return this; }
        public Builder format(MatchFormat v) { m.format = v; return this; }
        public Builder status(MatchStatus v) { m.status = v; return this; }
        public Builder venue(String v) { m.venue = v; return this; }
        public Builder scheduledAt(LocalDateTime v) { m.scheduledAt = v; return this; }
        public Builder startedAt(LocalDateTime v) { m.startedAt = v; return this; }
        public Builder completedAt(LocalDateTime v) { m.completedAt = v; return this; }
        public Builder tossWinner(String v) { m.tossWinner = v; return this; }
        public Builder tossDecision(String v) { m.tossDecision = v; return this; }
        public Builder maxOvers(Integer v) { m.maxOvers = v; return this; }
        public Builder isLive(Boolean v) { m.isLive = v; return this; }
        public Builder currentInnings(Integer v) { m.currentInnings = v; return this; }
        public Builder battingTeam(String v) { m.battingTeam = v; return this; }
        public Builder bowlingTeam(String v) { m.bowlingTeam = v; return this; }
        public Builder result(String v) { m.result = v; return this; }
        public Builder winnerTeamName(String v) { m.winnerTeamName = v; return this; }
        public Builder targetRuns(Integer v) { m.targetRuns = v; return this; }
        public Match build() { return m; }
    }
}
