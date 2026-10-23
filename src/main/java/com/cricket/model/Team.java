package com.cricket.model;

import jakarta.persistence.*;

/**
 * Entity representing a cricket team.
 */
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 3)
    private String shortName;

    private String country;
    private String flagEmoji;

    @Column(nullable = false)
    private Integer matchesPlayed = 0;
    private Integer wins = 0;
    private Integer losses = 0;
    private Integer draws = 0;

    public Team() {}

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
    public void setMatchesPlayed(Integer matchesPlayed) { this.matchesPlayed = matchesPlayed; }
    public Integer getWins() { return wins; }
    public void setWins(Integer wins) { this.wins = wins; }
    public Integer getLosses() { return losses; }
    public void setLosses(Integer losses) { this.losses = losses; }
    public Integer getDraws() { return draws; }
    public void setDraws(Integer draws) { this.draws = draws; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Team t = new Team();
        public Builder id(Long v) { t.id = v; return this; }
        public Builder name(String v) { t.name = v; return this; }
        public Builder shortName(String v) { t.shortName = v; return this; }
        public Builder country(String v) { t.country = v; return this; }
        public Builder flagEmoji(String v) { t.flagEmoji = v; return this; }
        public Builder matchesPlayed(Integer v) { t.matchesPlayed = v; return this; }
        public Builder wins(Integer v) { t.wins = v; return this; }
        public Builder losses(Integer v) { t.losses = v; return this; }
        public Builder draws(Integer v) { t.draws = v; return this; }
        public Team build() { return t; }
    }
}
