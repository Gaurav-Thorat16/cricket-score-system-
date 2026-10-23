package com.cricket.model;

import jakarta.persistence.*;

/**
 * Entity representing a cricket player with career statistics.
 */
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    private Integer jerseyNumber;

    private Integer careerRuns = 0;
    private Integer careerBallsFaced = 0;
    private Integer careerFours = 0;
    private Integer careerSixes = 0;
    private Integer careerFifties = 0;
    private Integer careerHundreds = 0;
    private Integer careerMatchesBatted = 0;
    private Integer careerHighScore = 0;
    private Integer careerWickets = 0;
    private Integer careerRunsConceded = 0;
    private Integer careerOversBowled = 0;
    private Integer careerMatchesBowled = 0;
    private Integer careerBestBowlingWickets = 0;
    private Integer careerBestBowlingRuns = 999;

    public Player() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public Integer getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(Integer jerseyNumber) { this.jerseyNumber = jerseyNumber; }
    public Integer getCareerRuns() { return careerRuns; }
    public void setCareerRuns(Integer v) { this.careerRuns = v; }
    public Integer getCareerBallsFaced() { return careerBallsFaced; }
    public void setCareerBallsFaced(Integer v) { this.careerBallsFaced = v; }
    public Integer getCareerFours() { return careerFours; }
    public void setCareerFours(Integer v) { this.careerFours = v; }
    public Integer getCareerSixes() { return careerSixes; }
    public void setCareerSixes(Integer v) { this.careerSixes = v; }
    public Integer getCareerFifties() { return careerFifties; }
    public void setCareerFifties(Integer v) { this.careerFifties = v; }
    public Integer getCareerHundreds() { return careerHundreds; }
    public void setCareerHundreds(Integer v) { this.careerHundreds = v; }
    public Integer getCareerMatchesBatted() { return careerMatchesBatted; }
    public void setCareerMatchesBatted(Integer v) { this.careerMatchesBatted = v; }
    public Integer getCareerHighScore() { return careerHighScore; }
    public void setCareerHighScore(Integer v) { this.careerHighScore = v; }
    public Integer getCareerWickets() { return careerWickets; }
    public void setCareerWickets(Integer v) { this.careerWickets = v; }
    public Integer getCareerRunsConceded() { return careerRunsConceded; }
    public void setCareerRunsConceded(Integer v) { this.careerRunsConceded = v; }
    public Integer getCareerOversBowled() { return careerOversBowled; }
    public void setCareerOversBowled(Integer v) { this.careerOversBowled = v; }
    public Integer getCareerMatchesBowled() { return careerMatchesBowled; }
    public void setCareerMatchesBowled(Integer v) { this.careerMatchesBowled = v; }
    public Integer getCareerBestBowlingWickets() { return careerBestBowlingWickets; }
    public void setCareerBestBowlingWickets(Integer v) { this.careerBestBowlingWickets = v; }
    public Integer getCareerBestBowlingRuns() { return careerBestBowlingRuns; }
    public void setCareerBestBowlingRuns(Integer v) { this.careerBestBowlingRuns = v; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Player p = new Player();
        public Builder name(String v) { p.name = v; return this; }
        public Builder role(String v) { p.role = v; return this; }
        public Builder team(Team v) { p.team = v; return this; }
        public Builder jerseyNumber(Integer v) { p.jerseyNumber = v; return this; }
        public Builder careerRuns(Integer v) { p.careerRuns = v; return this; }
        public Builder careerBallsFaced(Integer v) { p.careerBallsFaced = v; return this; }
        public Builder careerFours(Integer v) { p.careerFours = v; return this; }
        public Builder careerSixes(Integer v) { p.careerSixes = v; return this; }
        public Builder careerFifties(Integer v) { p.careerFifties = v; return this; }
        public Builder careerHundreds(Integer v) { p.careerHundreds = v; return this; }
        public Builder careerMatchesBatted(Integer v) { p.careerMatchesBatted = v; return this; }
        public Builder careerHighScore(Integer v) { p.careerHighScore = v; return this; }
        public Builder careerWickets(Integer v) { p.careerWickets = v; return this; }
        public Builder careerRunsConceded(Integer v) { p.careerRunsConceded = v; return this; }
        public Builder careerOversBowled(Integer v) { p.careerOversBowled = v; return this; }
        public Builder careerMatchesBowled(Integer v) { p.careerMatchesBowled = v; return this; }
        public Builder careerBestBowlingWickets(Integer v) { p.careerBestBowlingWickets = v; return this; }
        public Builder careerBestBowlingRuns(Integer v) { p.careerBestBowlingRuns = v; return this; }
        public Player build() { return p; }
    }
}
