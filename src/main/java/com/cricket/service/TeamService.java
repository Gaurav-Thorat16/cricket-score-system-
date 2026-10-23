package com.cricket.service;

import com.cricket.dto.TeamDTO;
import com.cricket.model.Team;
import com.cricket.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private static final Logger log = LoggerFactory.getLogger(TeamService.class);
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TeamDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found: " + id));
        return toDTO(team);
    }

    @Transactional
    public TeamDTO createTeam(TeamDTO dto) {
        if (teamRepository.existsByShortName(dto.getShortName())) {
            throw new RuntimeException("Team with short name already exists: " + dto.getShortName());
        }
        Team team = Team.builder()
                .name(dto.getName())
                .shortName(dto.getShortName().toUpperCase())
                .country(dto.getCountry())
                .flagEmoji(dto.getFlagEmoji())
                .matchesPlayed(0)
                .wins(0)
                .losses(0)
                .draws(0)
                .build();
        team = teamRepository.save(team);
        log.info("Created team: {} ({})", team.getName(), team.getShortName());
        return toDTO(team);
    }

    public TeamDTO toDTO(Team team) {
        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .shortName(team.getShortName())
                .country(team.getCountry())
                .flagEmoji(team.getFlagEmoji())
                .matchesPlayed(team.getMatchesPlayed())
                .wins(team.getWins())
                .losses(team.getLosses())
                .draws(team.getDraws())
                .build();
    }
}
