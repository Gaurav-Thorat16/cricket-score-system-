package com.cricket.dto;

public class TeamDTO {
    private Long id;
    private String name;
    private String shortName;
    private String country;
    private String flagEmoji;
    private Integer matchesPlayed;
    private Integer wins;
    private Integer losses;
    private Integer draws;

    public TeamDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getShortName() { return shortName; }
    public void setShortName(String shortName) { this.shortName = shortName; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getFlagEmoji() { return flagEmoji; }
    public void setFlagEmoji(String flagEmoji) { this.flagEmoji = flagEmoji; }
    public Integer getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(Integer v) { this.matchesPlayed = v; }
    public Integer getWins() { return wins; }
    public void setWins(Integer v) { this.wins = v; }
    public Integer getLosses() { return losses; }
    public void setLosses(Integer v) { this.losses = v; }
    public Integer getDraws() { return draws; }
    public void setDraws(Integer v) { this.draws = v; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final TeamDTO d = new TeamDTO();
        public Builder id(Long v) { d.id = v; return this; }
        public Builder name(String v) { d.name = v; return this; }
        public Builder shortName(String v) { d.shortName = v; return this; }
        public Builder country(String v) { d.country = v; return this; }
        public Builder flagEmoji(String v) { d.flagEmoji = v; return this; }
        public Builder matchesPlayed(Integer v) { d.matchesPlayed = v; return this; }
        public Builder wins(Integer v) { d.wins = v; return this; }
        public Builder losses(Integer v) { d.losses = v; return this; }
        public Builder draws(Integer v) { d.draws = v; return this; }
        public TeamDTO build() { return d; }
    }
}
