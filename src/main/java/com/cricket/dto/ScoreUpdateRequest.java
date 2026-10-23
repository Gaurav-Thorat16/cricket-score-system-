package com.cricket.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ScoreUpdateRequest {

    @NotNull(message = "Match ID is required")
    private Long matchId;

    @NotNull(message = "Batsman player ID is required")
    private Long batsmanId;

    @NotNull(message = "Bowler player ID is required")
    private Long bowlerId;

    @NotNull(message = "Runs scored is required")
    @Min(value = 0)
    private Integer runsScored;

    private Boolean isWicket = false;
    private String wicketType;
    private Long fielderId;
    private Boolean isWide = false;
    private Boolean isNoBall = false;

    public ScoreUpdateRequest() {}

    public Long getMatchId() { return matchId; }
    public void setMatchId(Long matchId) { this.matchId = matchId; }
    public Long getBatsmanId() { return batsmanId; }
    public void setBatsmanId(Long batsmanId) { this.batsmanId = batsmanId; }
    public Long getBowlerId() { return bowlerId; }
    public void setBowlerId(Long bowlerId) { this.bowlerId = bowlerId; }
    public Integer getRunsScored() { return runsScored; }
    public void setRunsScored(Integer runsScored) { this.runsScored = runsScored; }
    public Boolean getIsWicket() { return isWicket; }
    public void setIsWicket(Boolean wicket) { isWicket = wicket; }
    public String getWicketType() { return wicketType; }
    public void setWicketType(String wicketType) { this.wicketType = wicketType; }
    public Long getFielderId() { return fielderId; }
    public void setFielderId(Long fielderId) { this.fielderId = fielderId; }
    public Boolean getIsWide() { return isWide; }
    public void setIsWide(Boolean wide) { isWide = wide; }
    public Boolean getIsNoBall() { return isNoBall; }
    public void setIsNoBall(Boolean noBall) { isNoBall = noBall; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ScoreUpdateRequest r = new ScoreUpdateRequest();
        public Builder matchId(Long v) { r.matchId = v; return this; }
        public Builder batsmanId(Long v) { r.batsmanId = v; return this; }
        public Builder bowlerId(Long v) { r.bowlerId = v; return this; }
        public Builder runsScored(Integer v) { r.runsScored = v; return this; }
        public Builder isWicket(Boolean v) { r.isWicket = v; return this; }
        public Builder wicketType(String v) { r.wicketType = v; return this; }
        public Builder fielderId(Long v) { r.fielderId = v; return this; }
        public Builder isWide(Boolean v) { r.isWide = v; return this; }
        public Builder isNoBall(Boolean v) { r.isNoBall = v; return this; }
        public ScoreUpdateRequest build() { return r; }
    }
}
