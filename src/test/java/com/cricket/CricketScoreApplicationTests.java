package com.cricket;

import com.cricket.dto.CreateMatchRequest;
import com.cricket.dto.MatchDTO;
import com.cricket.dto.TeamDTO;
import com.cricket.model.MatchFormat;
import com.cricket.model.MatchStatus;
import com.cricket.repository.TeamRepository;
import com.cricket.service.MatchService;
import com.cricket.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Integration tests for core cricket match management operations.
 */
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CricketScoreApplicationTests {

    @Autowired MatchService matchService;
    @Autowired TeamService teamService;
    @Autowired TeamRepository teamRepository;

    @Test
    void contextLoads() {
        assertThat(matchService).isNotNull();
        assertThat(teamService).isNotNull();
    }

    @Test
    void createTeam_success() {
        TeamDTO dto = TeamDTO.builder()
                .name("West Indies").shortName("WI").country("West Indies").flagEmoji("🏝️")
                .build();
        TeamDTO saved = teamService.createTeam(dto);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getShortName()).isEqualTo("WI");
    }

    @Test
    void createTeam_duplicateShortName_throws() {
        TeamDTO dto = TeamDTO.builder()
                .name("West Indies").shortName("IND").country("West Indies").build();
        assertThatThrownBy(() -> teamService.createTeam(dto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("IND");
    }

    @Test
    void createMatch_success() {
        var teams = teamRepository.findAll();
        assertThat(teams).hasSizeGreaterThanOrEqualTo(2);

        CreateMatchRequest req = CreateMatchRequest.builder()
                .title("Test Match")
                .team1Id(teams.get(0).getId())
                .team2Id(teams.get(1).getId())
                .format(MatchFormat.T20)
                .venue("Test Stadium")
                .tossWinner(teams.get(0).getShortName())
                .tossDecision("BAT")
                .build();

        MatchDTO match = matchService.createMatch(req);
        assertThat(match.getId()).isNotNull();
        assertThat(match.getStatus()).isEqualTo(MatchStatus.SCHEDULED);
        assertThat(match.getMaxOvers()).isEqualTo(20);
    }

    @Test
    void startMatch_scheduledToLive() {
        var teams = teamRepository.findAll();
        CreateMatchRequest req = CreateMatchRequest.builder()
                .title("Start Test")
                .team1Id(teams.get(0).getId())
                .team2Id(teams.get(1).getId())
                .format(MatchFormat.ODI)
                .venue("Stadium")
                .tossWinner(teams.get(0).getShortName())
                .tossDecision("BOWL")
                .build();
        MatchDTO match = matchService.createMatch(req);
        MatchDTO live = matchService.startMatch(match.getId());
        assertThat(live.getStatus()).isEqualTo(MatchStatus.LIVE);
        assertThat(live.getIsLive()).isTrue();
        assertThat(live.getBattingTeam()).isNotNull();
    }

    @Test
    void startMatch_alreadyLive_throws() {
        var teams = teamRepository.findAll();
        CreateMatchRequest req = CreateMatchRequest.builder()
                .title("Already Live").team1Id(teams.get(0).getId()).team2Id(teams.get(1).getId())
                .format(MatchFormat.T20).venue("Stadium").build();
        MatchDTO match = matchService.createMatch(req);
        matchService.startMatch(match.getId());
        assertThatThrownBy(() -> matchService.startMatch(match.getId()))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void getAllMatches_returnsSeededMatches() {
        var matches = matchService.getAllMatches();
        assertThat(matches).isNotEmpty();
    }

    @Test
    void getLiveMatches_returnsOnlyLive() {
        var live = matchService.getLiveMatches();
        live.forEach(m -> assertThat(m.getStatus())
                .isIn(MatchStatus.LIVE, MatchStatus.INNINGS_BREAK));
    }

    @Test
    void completeMatch_setsResult() {
        var teams = teamRepository.findAll();
        CreateMatchRequest req = CreateMatchRequest.builder()
                .title("Complete Test").team1Id(teams.get(0).getId()).team2Id(teams.get(1).getId())
                .format(MatchFormat.T20).venue("Stadium").build();
        MatchDTO match = matchService.createMatch(req);
        MatchDTO completed = matchService.completeMatch(match.getId(), "India won by 20 runs");
        assertThat(completed.getStatus()).isEqualTo(MatchStatus.COMPLETED);
        assertThat(completed.getResult()).contains("India won");
    }
}

