package com.cricket.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a single ball delivery with commentary.
 */
@Entity
@Table(name = "score_updates")
public class ScoreUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(nullable = false)
    private Integer inningsNumber;

    @Column(nullable = false)
    private Integer overNumber;

    @Column(nullable = false)
    private Integer ballNumber;

    @Column(nullable = false)
    private Integer runsScored;

    private Boolean isWicket;
    private String wicketType;
    private Boolean isWide;
    private Boolean isNoBall;
    private Boolean isFour;
    private Boolean isSix;

    private String batsman;
    private String bowler;
    private String fielder;

    @Column(length = 400)
    private String commentary;

    private Integer totalRuns;
    private Integer totalWickets;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    public ScoreUpdate() {}

    public Long getId() { return id; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public Integer getInningsNumber() { return inningsNumber; }
    public void setInningsNumber(Integer v) { this.inningsNumber = v; }
    public Integer getOverNumber() { return overNumber; }
    public void setOverNumber(Integer v) { this.overNumber = v; }
    public Integer getBallNumber() { return ballNumber; }
    public void setBallNumber(Integer v) { this.ballNumber = v; }
    public Integer getRunsScored() { return runsScored; }
    public void setRunsScored(Integer v) { this.runsScored = v; }
    public Boolean getIsWicket() { return isWicket; }
    public void setIsWicket(Boolean v) { this.isWicket = v; }
    public String getWicketType() { return wicketType; }
    public void setWicketType(String v) { this.wicketType = v; }
    public Boolean getIsWide() { return isWide; }
    public void setIsWide(Boolean v) { this.isWide = v; }
    public Boolean getIsNoBall() { return isNoBall; }
    public void setIsNoBall(Boolean v) { this.isNoBall = v; }
    public Boolean getIsFour() { return isFour; }
    public void setIsFour(Boolean v) { this.isFour = v; }
    public Boolean getIsSix() { return isSix; }
    public void setIsSix(Boolean v) { this.isSix = v; }
    public String getBatsman() { return batsman; }
    public void setBatsman(String v) { this.batsman = v; }
    public String getBowler() { return bowler; }
    public void setBowler(String v) { this.bowler = v; }
    public String getFielder() { return fielder; }
    public void setFielder(String v) { this.fielder = v; }
    public String getCommentary() { return commentary; }
    public void setCommentary(String v) { this.commentary = v; }
    public Integer getTotalRuns() { return totalRuns; }
    public void setTotalRuns(Integer v) { this.totalRuns = v; }
    public Integer getTotalWickets() { return totalWickets; }
    public void setTotalWickets(Integer v) { this.totalWickets = v; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime v) { this.timestamp = v; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ScoreUpdate s = new ScoreUpdate();
        public Builder match(Match v) { s.match = v; return this; }
        public Builder inningsNumber(Integer v) { s.inningsNumber = v; return this; }
        public Builder overNumber(Integer v) { s.overNumber = v; return this; }
        public Builder ballNumber(Integer v) { s.ballNumber = v; return this; }
        public Builder runsScored(Integer v) { s.runsScored = v; return this; }
        public Builder isWicket(Boolean v) { s.isWicket = v; return this; }
        public Builder wicketType(String v) { s.wicketType = v; return this; }
        public Builder isWide(Boolean v) { s.isWide = v; return this; }
        public Builder isNoBall(Boolean v) { s.isNoBall = v; return this; }
        public Builder isFour(Boolean v) { s.isFour = v; return this; }
        public Builder isSix(Boolean v) { s.isSix = v; return this; }
        public Builder batsman(String v) { s.batsman = v; return this; }
        public Builder bowler(String v) { s.bowler = v; return this; }
        public Builder fielder(String v) { s.fielder = v; return this; }
        public Builder commentary(String v) { s.commentary = v; return this; }
        public Builder totalRuns(Integer v) { s.totalRuns = v; return this; }
        public Builder totalWickets(Integer v) { s.totalWickets = v; return this; }
        public Builder timestamp(LocalDateTime v) { s.timestamp = v; return this; }
        public ScoreUpdate build() { return s; }
    }
}
