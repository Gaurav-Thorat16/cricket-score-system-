package com.cricket.dto;

import java.time.LocalDateTime;

public class ScoreUpdateDTO {
    private Long id;
    private Long matchId;
    private Integer inningsNumber;
    private Integer overNumber;
    private Integer ballNumber;
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
    private String commentary;
    private Integer totalRuns;
    private Integer totalWickets;
    private LocalDateTime timestamp;

    public ScoreUpdateDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMatchId() { return matchId; }
    public void setMatchId(Long matchId) { this.matchId = matchId; }
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
        private final ScoreUpdateDTO d = new ScoreUpdateDTO();
        public Builder id(Long v) { d.id = v; return this; }
        public Builder matchId(Long v) { d.matchId = v; return this; }
        public Builder inningsNumber(Integer v) { d.inningsNumber = v; return this; }
        public Builder overNumber(Integer v) { d.overNumber = v; return this; }
        public Builder ballNumber(Integer v) { d.ballNumber = v; return this; }
        public Builder runsScored(Integer v) { d.runsScored = v; return this; }
        public Builder isWicket(Boolean v) { d.isWicket = v; return this; }
        public Builder wicketType(String v) { d.wicketType = v; return this; }
        public Builder isWide(Boolean v) { d.isWide = v; return this; }
        public Builder isNoBall(Boolean v) { d.isNoBall = v; return this; }
        public Builder isFour(Boolean v) { d.isFour = v; return this; }
        public Builder isSix(Boolean v) { d.isSix = v; return this; }
        public Builder batsman(String v) { d.batsman = v; return this; }
        public Builder bowler(String v) { d.bowler = v; return this; }
        public Builder fielder(String v) { d.fielder = v; return this; }
        public Builder commentary(String v) { d.commentary = v; return this; }
        public Builder totalRuns(Integer v) { d.totalRuns = v; return this; }
        public Builder totalWickets(Integer v) { d.totalWickets = v; return this; }
        public Builder timestamp(LocalDateTime v) { d.timestamp = v; return this; }
        public ScoreUpdateDTO build() { return d; }
    }
}
