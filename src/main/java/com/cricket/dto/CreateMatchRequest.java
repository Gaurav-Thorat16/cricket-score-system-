package com.cricket.dto;

import com.cricket.model.MatchFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateMatchRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Team 1 ID is required")
    private Long team1Id;

    @NotNull(message = "Team 2 ID is required")
    private Long team2Id;

    @NotNull(message = "Match format is required")
    private MatchFormat format;

    @NotBlank(message = "Venue is required")
    private String venue;

    private LocalDateTime scheduledAt;
    private String tossWinner;
    private String tossDecision;

    public CreateMatchRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Long getTeam1Id() { return team1Id; }
    public void setTeam1Id(Long team1Id) { this.team1Id = team1Id; }
    public Long getTeam2Id() { return team2Id; }
    public void setTeam2Id(Long team2Id) { this.team2Id = team2Id; }
    public MatchFormat getFormat() { return format; }
    public void setFormat(MatchFormat format) { this.format = format; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    public String getTossWinner() { return tossWinner; }
    public void setTossWinner(String tossWinner) { this.tossWinner = tossWinner; }
    public String getTossDecision() { return tossDecision; }
    public void setTossDecision(String tossDecision) { this.tossDecision = tossDecision; }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final CreateMatchRequest r = new CreateMatchRequest();
        public Builder title(String v) { r.title = v; return this; }
        public Builder team1Id(Long v) { r.team1Id = v; return this; }
        public Builder team2Id(Long v) { r.team2Id = v; return this; }
        public Builder format(MatchFormat v) { r.format = v; return this; }
        public Builder venue(String v) { r.venue = v; return this; }
        public Builder scheduledAt(LocalDateTime v) { r.scheduledAt = v; return this; }
        public Builder tossWinner(String v) { r.tossWinner = v; return this; }
        public Builder tossDecision(String v) { r.tossDecision = v; return this; }
        public CreateMatchRequest build() { return r; }
    }
}
