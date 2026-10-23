package com.cricket.repository;

import com.cricket.model.PlayerMatchStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerMatchStatRepository extends JpaRepository<PlayerMatchStat, Long> {

    List<PlayerMatchStat> findByMatchId(Long matchId);

    List<PlayerMatchStat> findByMatchIdAndInningsNumber(Long matchId, Integer inningsNumber);

    Optional<PlayerMatchStat> findByMatchIdAndPlayerIdAndInningsNumber(Long matchId, Long playerId, Integer inningsNumber);

    @Query("SELECT p FROM PlayerMatchStat p WHERE p.match.id = :matchId AND p.isBatting = true")
    List<PlayerMatchStat> findCurrentBatsmen(Long matchId);

    @Query("SELECT p FROM PlayerMatchStat p WHERE p.match.id = :matchId AND p.isBowling = true")
    List<PlayerMatchStat> findCurrentBowler(Long matchId);

    @Query("SELECT p FROM PlayerMatchStat p WHERE p.match.id = :matchId AND p.inningsNumber = :innings ORDER BY p.battingPosition ASC")
    List<PlayerMatchStat> findBattingOrder(Long matchId, Integer innings);
}

