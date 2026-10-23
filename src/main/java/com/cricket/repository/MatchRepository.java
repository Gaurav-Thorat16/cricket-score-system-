package com.cricket.repository;

import com.cricket.model.Match;
import com.cricket.model.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByStatus(MatchStatus status);

    List<Match> findByIsLiveTrue();

    @Query("SELECT m FROM Match m WHERE m.status = 'LIVE' OR m.status = 'INNINGS_BREAK' ORDER BY m.startedAt DESC")
    List<Match> findAllLiveMatches();

    @Query("SELECT m FROM Match m WHERE m.status = 'COMPLETED' ORDER BY m.completedAt DESC")
    List<Match> findRecentCompletedMatches();

    @Query("SELECT m FROM Match m WHERE m.team1.id = :teamId OR m.team2.id = :teamId ORDER BY m.scheduledAt DESC")
    List<Match> findMatchesByTeamId(Long teamId);
}

