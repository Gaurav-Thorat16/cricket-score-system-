package com.cricket.dto;

public class PlayerDTO {
    private Long id;
    private String name;
    private String role;
    private Long teamId;
    private String teamName;
    private String teamShortName;
    private Integer jerseyNumber;
    private Integer careerRuns;
    private Integer careerBallsFaced;
    private Integer careerFours;
    private Integer careerSixes;
    private Integer careerFifties;
    private Integer careerHundreds;
    private Integer careerMatchesBatted;
    private Integer careerHighScore;
    private Double careerBattingAverage;
    private Double careerStrikeRate;
    private Integer careerWickets;
    private Integer careerRunsConceded;
    private Integer careerOversBowled;
    private Integer careerMatchesBowled;
    private Double careerBowlingAverage;
    private Double careerEconomyRate;
    private String careerBestBowling;

    public PlayerDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getTeamShortName() { return teamShortName; }
    public void setTeamShortName(String teamShortName) { this.teamShortName = teamShortName; }
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
    public Double getCareerBattingAverage() { return careerBattingAverage; }
    public void setCareerBattingAverage(Double v) { this.careerBattingAverage = v; }
    public Double getCareerStrikeRate() { return careerStrikeRate; }
    public void setCareerStrikeRate(Double v) { this.careerStrikeRate = v; }
    public Integer getCareerWickets() { return careerWickets; }
    public void setCareerWickets(Integer v) { this.careerWickets = v; }
    public Integer getCareerRunsConceded() { return careerRunsConceded; }
    public void setCareerRunsConceded(Integer v) { this.careerRunsConceded = v; }
    public Integer getCareerOversBowled() { return careerOversBowled; }
    public void setCareerOversBowled(Integer v) { this.careerOversBowled = v; }
    public Integer getCareerMatchesBowled() { return careerMatchesBowled; }
    public void setCareerMatchesBowled(Integer v) { this.careerMatchesBowled = v; }
    public Double getCareerBowlingAverage() { return careerBowlingAverage; }
    public void setCareerBowlingAverage(Double v) { this.careerBowlingAverage = v; }
    public Double getCareerEconomyRate() { return careerEconomyRate; }
    public void setCareerEconomyRate(Double v) { this.careerEconomyRate = v; }
    public String getCareerBestBowling() { return careerBestBowling; }
    public void setCareerBestBowling(String v) { this.careerBestBowling = v; }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final PlayerDTO d = new PlayerDTO();
        public Builder id(Long v) { d.id = v; return this; }
        public Builder name(String v) { d.name = v; return this; }
        public Builder role(String v) { d.role = v; return this; }
        public Builder teamId(Long v) { d.teamId = v; return this; }
        public Builder teamName(String v) { d.teamName = v; return this; }
        public Builder teamShortName(String v) { d.teamShortName = v; return this; }
        public Builder jerseyNumber(Integer v) { d.jerseyNumber = v; return this; }
        public Builder careerRuns(Integer v) { d.careerRuns = v; return this; }
        public Builder careerBallsFaced(Integer v) { d.careerBallsFaced = v; return this; }
        public Builder careerFours(Integer v) { d.careerFours = v; return this; }
        public Builder careerSixes(Integer v) { d.careerSixes = v; return this; }
        public Builder careerFifties(Integer v) { d.careerFifties = v; return this; }
        public Builder careerHundreds(Integer v) { d.careerHundreds = v; return this; }
        public Builder careerMatchesBatted(Integer v) { d.careerMatchesBatted = v; return this; }
        public Builder careerHighScore(Integer v) { d.careerHighScore = v; return this; }
        public Builder careerBattingAverage(Double v) { d.careerBattingAverage = v; return this; }
        public Builder careerStrikeRate(Double v) { d.careerStrikeRate = v; return this; }
        public Builder careerWickets(Integer v) { d.careerWickets = v; return this; }
        public Builder careerRunsConceded(Integer v) { d.careerRunsConceded = v; return this; }
        public Builder careerOversBowled(Integer v) { d.careerOversBowled = v; return this; }
        public Builder careerMatchesBowled(Integer v) { d.careerMatchesBowled = v; return this; }
        public Builder careerBowlingAverage(Double v) { d.careerBowlingAverage = v; return this; }
        public Builder careerEconomyRate(Double v) { d.careerEconomyRate = v; return this; }
        public Builder careerBestBowling(String v) { d.careerBestBowling = v; return this; }
        public PlayerDTO build() { return d; }
    }
}
