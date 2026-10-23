package com.cricket.repository;

import com.cricket.model.ScoreUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreUpdateRepository extends JpaRepository<ScoreUpdate, Long> {

    List<ScoreUpdate> findByMatchIdOrderByTimestampDesc(Long matchId);

    List<ScoreUpdate> findByMatchIdAndInningsNumberOrderByOverNumberAscBallNumberAsc(Long matchId, Integer inningsNumber);

    @Query("SELECT s FROM ScoreUpdate s WHERE s.match.id = :matchId ORDER BY s.timestamp DESC LIMIT 20")
    List<ScoreUpdate> findLast20Updates(Long matchId);

    @Query("SELECT s FROM ScoreUpdate s WHERE s.match.id = :matchId AND s.inningsNumber = :innings AND s.overNumber = :over ORDER BY s.ballNumber ASC")
    List<ScoreUpdate> findOverDetails(Long matchId, Integer innings, Integer over);

    @Query("SELECT COUNT(s) FROM ScoreUpdate s WHERE s.match.id = :matchId AND s.isWicket = true")
    Integer countWickets(Long matchId);
}

