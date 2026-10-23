package com.cricket.service;

import com.cricket.dto.PlayerDTO;
import com.cricket.model.Player;
import com.cricket.model.Team;
import com.cricket.repository.PlayerRepository;
import com.cricket.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private static final Logger log = LoggerFactory.getLogger(PlayerService.class);
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> getPlayersByTeam(Long teamId) {
        return playerRepository.findByTeamId(teamId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found: " + id));
        return toDTO(player);
    }

    @Transactional
    public PlayerDTO createPlayer(PlayerDTO dto) {
        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found: " + dto.getTeamId()));
        Player player = Player.builder()
                .name(dto.getName())
                .role(dto.getRole())
                .team(team)
                .jerseyNumber(dto.getJerseyNumber())
                .build();
        player = playerRepository.save(player);
        log.info("Created player: {} for team {}", player.getName(), team.getShortName());
        return toDTO(player);
    }

    public PlayerDTO toDTO(Player player) {
        double battingAvg = (player.getCareerMatchesBatted() > 0 && player.getCareerRuns() > 0)
                ? (double) player.getCareerRuns() / Math.max(1, player.getCareerMatchesBatted())
                : 0.0;
        double strikeRate = (player.getCareerBallsFaced() > 0)
                ? ((double) player.getCareerRuns() / player.getCareerBallsFaced()) * 100
                : 0.0;
        double bowlingAvg = (player.getCareerWickets() > 0)
                ? (double) player.getCareerRunsConceded() / player.getCareerWickets()
                : 0.0;
        double economy = (player.getCareerOversBowled() > 0)
                ? (double) player.getCareerRunsConceded() / player.getCareerOversBowled()
                : 0.0;

        String bestBowling = player.getCareerBestBowlingWickets() + "/" + player.getCareerBestBowlingRuns();
        if (player.getCareerBestBowlingWickets() == 0) bestBowling = "0/-";

        return PlayerDTO.builder()
                .id(player.getId())
                .name(player.getName())
                .role(player.getRole())
                .teamId(player.getTeam().getId())
                .teamName(player.getTeam().getName())
                .teamShortName(player.getTeam().getShortName())
                .jerseyNumber(player.getJerseyNumber())
                .careerRuns(player.getCareerRuns())
                .careerBallsFaced(player.getCareerBallsFaced())
                .careerFours(player.getCareerFours())
                .careerSixes(player.getCareerSixes())
                .careerFifties(player.getCareerFifties())
                .careerHundreds(player.getCareerHundreds())
                .careerMatchesBatted(player.getCareerMatchesBatted())
                .careerHighScore(player.getCareerHighScore())
                .careerBattingAverage(Math.round(battingAvg * 100.0) / 100.0)
                .careerStrikeRate(Math.round(strikeRate * 100.0) / 100.0)
                .careerWickets(player.getCareerWickets())
                .careerRunsConceded(player.getCareerRunsConceded())
                .careerOversBowled(player.getCareerOversBowled())
                .careerMatchesBowled(player.getCareerMatchesBowled())
                .careerBowlingAverage(Math.round(bowlingAvg * 100.0) / 100.0)
                .careerEconomyRate(Math.round(economy * 100.0) / 100.0)
                .careerBestBowling(bestBowling)
                .build();
    }
}
