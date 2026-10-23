package com.cricket.model;

import jakarta.persistence.*;

/**
 * Entity tracking a player's performance in a specific match innings.
 */
@Entity
@Table(name = "player_match_stats",
       uniqueConstraints = @UniqueConstraint(columnNames = {"match_id", "player_id", "innings_number"}))
public class PlayerMatchStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(nullable = false)
    private Integer inningsNumber = 1;

    private Integer runsScored = 0;
    private Integer ballsFaced = 0;
    private Integer fours = 0;
    private Integer sixes = 0;
    private Boolean isOut;
    private String dismissalType;
    private String dismissedBy;
    private Boolean isBatting = false;
    private Integer battingPosition = 0;
    private Integer oversBowled = 0;
    private Integer ballsBowled = 0;
    private Integer runsConceded = 0;
    private Integer wicketsTaken = 0;
    private Integer maidens = 0;
    private Integer noBalls = 0;
    private Integer wides = 0;
    private Boolean isBowling = false;
    private Integer catches = 0;
    private Integer runOuts = 0;
    private Integer stumpings = 0;

    public PlayerMatchStat() {}

    public Long getId() { return id; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
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
    public Integer getCatches() { return catches; }
    public void setCatches(Integer v) { this.catches = v; }
    public Integer getRunOuts() { return runOuts; }
    public void setRunOuts(Integer v) { this.runOuts = v; }
    public Integer getStumpings() { return stumpings; }
    public void setStumpings(Integer v) { this.stumpings = v; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PlayerMatchStat s = new PlayerMatchStat();
        public Builder match(Match v) { s.match = v; return this; }
        public Builder player(Player v) { s.player = v; return this; }
        public Builder inningsNumber(Integer v) { s.inningsNumber = v; return this; }
        public Builder isBatting(Boolean v) { s.isBatting = v; return this; }
        public Builder isOut(Boolean v) { s.isOut = v; return this; }
        public Builder isBowling(Boolean v) { s.isBowling = v; return this; }
        public PlayerMatchStat build() { return s; }
    }
}
