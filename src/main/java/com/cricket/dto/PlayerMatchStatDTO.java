package com.cricket.dto;

public class PlayerMatchStatDTO {
    private Long id;
    private Long playerId;
    private String playerName;
    private String role;
    private Integer inningsNumber;
    private Integer runsScored;
    private Integer ballsFaced;
    private Integer fours;
    private Integer sixes;
    private Boolean isOut;
    private String dismissalType;
    private String dismissedBy;
    private Boolean isBatting;
    private Integer battingPosition;
    private Double strikeRate;
    private Integer oversBowled;
    private Integer ballsBowled;
    private Integer runsConceded;
    private Integer wicketsTaken;
    private Integer maidens;
    private Integer noBalls;
    private Integer wides;
    private Boolean isBowling;
    private Double economy;
    private Integer catches;
    private Integer runOuts;
    private Integer stumpings;

    public PlayerMatchStatDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long v) { this.playerId = v; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String v) { this.playerName = v; }
    public String getRole() { return role; }
    public void setRole(String v) { this.role = v; }
    public Integer getInningsNumber() { return inningsNumber; }
    public void setInningsNumber(Integer v) { this.inningsNumber = v; }
    public Integer getRunsScored() { return runsScored; }
    public void setRunsScored(Integer v) { this.runsScored = v; }
    public Integer getBallsFaced() { return ballsFaced; }
    public void setBallsFaced(Integer v) { this.ballsFaced = v; }
    public Integer getFours() { return fours; }
    public void setFours(Integer v) { this.fours = v; }
    public Integer getSixes() { return sixes; }
    public void setSixes(Integer v) { this.sixes = v; }
    public Boolean getIsOut() { return isOut; }
    public void setIsOut(Boolean v) { this.isOut = v; }
    public String getDismissalType() { return dismissalType; }
    public void setDismissalType(String v) { this.dismissalType = v; }
    public String getDismissedBy() { return dismissedBy; }
    public void setDismissedBy(String v) { this.dismissedBy = v; }
    public Boolean getIsBatting() { return isBatting; }
    public void setIsBatting(Boolean v) { this.isBatting = v; }
    public Integer getBattingPosition() { return battingPosition; }
    public void setBattingPosition(Integer v) { this.battingPosition = v; }
    public Double getStrikeRate() { return strikeRate; }
    public void setStrikeRate(Double v) { this.strikeRate = v; }
    public Integer getOversBowled() { return oversBowled; }
    public void setOversBowled(Integer v) { this.oversBowled = v; }
    public Integer getBallsBowled() { return ballsBowled; }
    public void setBallsBowled(Integer v) { this.ballsBowled = v; }
    public Integer getRunsConceded() { return runsConceded; }
    public void setRunsConceded(Integer v) { this.runsConceded = v; }
    public Integer getWicketsTaken() { return wicketsTaken; }
    public void setWicketsTaken(Integer v) { this.wicketsTaken = v; }
    public Integer getMaidens() { return maidens; }
    public void setMaidens(Integer v) { this.maidens = v; }
    public Integer getNoBalls() { return noBalls; }
    public void setNoBalls(Integer v) { this.noBalls = v; }
    public Integer getWides() { return wides; }
    public void setWides(Integer v) { this.wides = v; }
    public Boolean getIsBowling() { return isBowling; }
    public void setIsBowling(Boolean v) { this.isBowling = v; }
    public Double getEconomy() { return economy; }
    public void setEconomy(Double v) { this.economy = v; }
    public Integer getCatches() { return catches; }
    public void setCatches(Integer v) { this.catches = v; }
    public Integer getRunOuts() { return runOuts; }
    public void setRunOuts(Integer v) { this.runOuts = v; }
    public Integer getStumpings() { return stumpings; }
    public void setStumpings(Integer v) { this.stumpings = v; }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final PlayerMatchStatDTO d = new PlayerMatchStatDTO();
        public Builder id(Long v) { d.id = v; return this; }
        public Builder playerId(Long v) { d.playerId = v; return this; }
        public Builder playerName(String v) { d.playerName = v; return this; }
        public Builder role(String v) { d.role = v; return this; }
        public Builder inningsNumber(Integer v) { d.inningsNumber = v; return this; }
        public Builder runsScored(Integer v) { d.runsScored = v; return this; }
        public Builder ballsFaced(Integer v) { d.ballsFaced = v; return this; }
        public Builder fours(Integer v) { d.fours = v; return this; }
        public Builder sixes(Integer v) { d.sixes = v; return this; }
        public Builder isOut(Boolean v) { d.isOut = v; return this; }
        public Builder dismissalType(String v) { d.dismissalType = v; return this; }
        public Builder dismissedBy(String v) { d.dismissedBy = v; return this; }
        public Builder isBatting(Boolean v) { d.isBatting = v; return this; }
        public Builder battingPosition(Integer v) { d.battingPosition = v; return this; }
        public Builder strikeRate(Double v) { d.strikeRate = v; return this; }
        public Builder oversBowled(Integer v) { d.oversBowled = v; return this; }
        public Builder ballsBowled(Integer v) { d.ballsBowled = v; return this; }
        public Builder runsConceded(Integer v) { d.runsConceded = v; return this; }
        public Builder wicketsTaken(Integer v) { d.wicketsTaken = v; return this; }
        public Builder maidens(Integer v) { d.maidens = v; return this; }
        public Builder noBalls(Integer v) { d.noBalls = v; return this; }
        public Builder wides(Integer v) { d.wides = v; return this; }
        public Builder isBowling(Boolean v) { d.isBowling = v; return this; }
        public Builder economy(Double v) { d.economy = v; return this; }
        public Builder catches(Integer v) { d.catches = v; return this; }
        public Builder runOuts(Integer v) { d.runOuts = v; return this; }
        public Builder stumpings(Integer v) { d.stumpings = v; return this; }
        public PlayerMatchStatDTO build() { return d; }
    }
}
