package com.cricket.config;

import com.cricket.dto.CreateMatchRequest;
import com.cricket.model.*;
import com.cricket.repository.*;
import com.cricket.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final MatchService matchService;

    public DataSeeder(TeamRepository teamRepository,
                      PlayerRepository playerRepository,
                      MatchService matchService) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.matchService = matchService;
    }

    @Override
    public void run(String... args) {
        log.info("Seeding cricket database...");
        seedTeamsAndPlayers();
        seedMatches();
        log.info("Database seeded successfully!");
    }

    private void seedTeamsAndPlayers() {
        Team india = teamRepository.save(Team.builder()
                .name("India").shortName("IND").country("India").flagEmoji("🇮🇳")
                .matchesPlayed(120).wins(72).losses(38).draws(10).build());

        List<String[]> indiaPlayers = List.of(
                new String[]{"Rohit Sharma", "Batsman", "45"},
                new String[]{"Shubman Gill", "Batsman", "77"},
                new String[]{"Virat Kohli", "Batsman", "18"},
                new String[]{"Shreyas Iyer", "Batsman", "41"},
                new String[]{"KL Rahul", "Wicket-Keeper", "1"},
                new String[]{"Hardik Pandya", "All-Rounder", "33"},
                new String[]{"Ravindra Jadeja", "All-Rounder", "8"},
                new String[]{"Axar Patel", "All-Rounder", "20"},
                new String[]{"Jasprit Bumrah", "Bowler", "93"},
                new String[]{"Mohammed Siraj", "Bowler", "13"},
                new String[]{"Kuldeep Yadav", "Bowler", "23"}
        );
        savePlayersForTeam(india, indiaPlayers);

        Team australia = teamRepository.save(Team.builder()
                .name("Australia").shortName("AUS").country("Australia").flagEmoji("🇦🇺")
                .matchesPlayed(118).wins(68).losses(40).draws(10).build());

        List<String[]> ausPlayers = List.of(
                new String[]{"David Warner", "Batsman", "31"},
                new String[]{"Travis Head", "Batsman", "35"},
                new String[]{"Steven Smith", "Batsman", "49"},
                new String[]{"Marnus Labuschagne", "Batsman", "3"},
                new String[]{"Alex Carey", "Wicket-Keeper", "36"},
                new String[]{"Cameron Green", "All-Rounder", "20"},
                new String[]{"Glenn Maxwell", "All-Rounder", "32"},
                new String[]{"Mitchell Starc", "Bowler", "56"},
                new String[]{"Pat Cummins", "Bowler", "30"},
                new String[]{"Josh Hazlewood", "Bowler", "38"},
                new String[]{"Adam Zampa", "Bowler", "8"}
        );
        savePlayersForTeam(australia, ausPlayers);

        Team england = teamRepository.save(Team.builder()
                .name("England").shortName("ENG").country("England").flagEmoji("🏴󠁧󠁢󠁥󠁮󠁧󠁿")
                .matchesPlayed(115).wins(60).losses(45).draws(10).build());

        List<String[]> engPlayers = List.of(
                new String[]{"Zak Crawley", "Batsman", "16"},
                new String[]{"Ben Duckett", "Batsman", "17"},
                new String[]{"Joe Root", "Batsman", "66"},
                new String[]{"Jonny Bairstow", "Batsman", "4"},
                new String[]{"Ben Stokes", "All-Rounder", "55"},
                new String[]{"Jos Buttler", "Wicket-Keeper", "63"},
                new String[]{"Moeen Ali", "All-Rounder", "18"},
                new String[]{"Stuart Broad", "Bowler", "38"},
                new String[]{"James Anderson", "Bowler", "9"},
                new String[]{"Mark Wood", "Bowler", "33"},
                new String[]{"Liam Livingstone", "All-Rounder", "23"}
        );
        savePlayersForTeam(england, engPlayers);

        Team pakistan = teamRepository.save(Team.builder()
                .name("Pakistan").shortName("PAK").country("Pakistan").flagEmoji("🇵🇰")
                .matchesPlayed(112).wins(55).losses(47).draws(10).build());

        List<String[]> pakPlayers = List.of(
                new String[]{"Mohammad Rizwan", "Wicket-Keeper", "16"},
                new String[]{"Babar Azam", "Batsman", "56"},
                new String[]{"Fakhar Zaman", "Batsman", "14"},
                new String[]{"Mohammad Hafeez", "All-Rounder", "10"},
                new String[]{"Shadab Khan", "All-Rounder", "16"},
                new String[]{"Imam-ul-Haq", "Batsman", "7"},
                new String[]{"Shaheen Afridi", "Bowler", "10"},
                new String[]{"Haris Rauf", "Bowler", "25"},
                new String[]{"Naseem Shah", "Bowler", "71"},
                new String[]{"Iftikhar Ahmed", "All-Rounder", "18"},
                new String[]{"Sarfaraz Ahmed", "Wicket-Keeper", "29"}
        );
        savePlayersForTeam(pakistan, pakPlayers);

        Team newZealand = teamRepository.save(Team.builder()
                .name("New Zealand").shortName("NZ").country("New Zealand").flagEmoji("🇳🇿")
                .matchesPlayed(108).wins(58).losses(40).draws(10).build());

        List<String[]> nzPlayers = List.of(
                new String[]{"Devon Conway", "Batsman", "31"},
                new String[]{"Tom Latham", "Batsman", "3"},
                new String[]{"Kane Williamson", "Batsman", "22"},
                new String[]{"Ross Taylor", "Batsman", "11"},
                new String[]{"Tom Blundell", "Wicket-Keeper", "29"},
                new String[]{"Daryl Mitchell", "All-Rounder", "57"},
                new String[]{"Glenn Phillips", "All-Rounder", "24"},
                new String[]{"Trent Boult", "Bowler", "22"},
                new String[]{"Tim Southee", "Bowler", "38"},
                new String[]{"Matt Henry", "Bowler", "66"},
                new String[]{"Mitchell Santner", "All-Rounder", "11"}
        );
        savePlayersForTeam(newZealand, nzPlayers);

        Team southAfrica = teamRepository.save(Team.builder()
                .name("South Africa").shortName("SA").country("South Africa").flagEmoji("🇿🇦")
                .matchesPlayed(110).wins(56).losses(44).draws(10).build());

        List<String[]> saPlayers = List.of(
                new String[]{"Quinton de Kock", "Wicket-Keeper", "12"},
                new String[]{"Temba Bavuma", "Batsman", "17"},
                new String[]{"Aiden Markram", "Batsman", "12"},
                new String[]{"David Miller", "Batsman", "10"},
                new String[]{"Rassie van der Dussen", "Batsman", "11"},
                new String[]{"Marco Jansen", "All-Rounder", "64"},
                new String[]{"Keshav Maharaj", "All-Rounder", "49"},
                new String[]{"Kagiso Rabada", "Bowler", "25"},
                new String[]{"Anrich Nortje", "Bowler", "89"},
                new String[]{"Lungi Ngidi", "Bowler", "15"},
                new String[]{"Wayne Parnell", "All-Rounder", "60"}
        );
        savePlayersForTeam(southAfrica, saPlayers);

        log.info("Teams and players seeded.");
    }

    private void savePlayersForTeam(Team team, List<String[]> players) {
        for (String[] p : players) {
            playerRepository.save(Player.builder()
                    .name(p[0])
                    .role(p[1])
                    .team(team)
                    .jerseyNumber(Integer.parseInt(p[2]))
                    .careerRuns((int)(Math.random() * 8000 + 2000))
                    .careerBallsFaced((int)(Math.random() * 9000 + 3000))
                    .careerFours((int)(Math.random() * 700 + 100))
                    .careerSixes((int)(Math.random() * 200 + 20))
                    .careerFifties((int)(Math.random() * 60 + 10))
                    .careerHundreds((int)(Math.random() * 30 + 2))
                    .careerMatchesBatted((int)(Math.random() * 120 + 30))
                    .careerHighScore((int)(Math.random() * 150 + 50))
                    .careerWickets("Bowler".equals(p[1]) || "All-Rounder".equals(p[1])
                            ? (int)(Math.random() * 200 + 20) : (int)(Math.random() * 10))
                    .careerRunsConceded((int)(Math.random() * 5000 + 500))
                    .careerOversBowled((int)(Math.random() * 300 + 50))
                    .careerMatchesBowled("Bowler".equals(p[1]) || "All-Rounder".equals(p[1])
                            ? (int)(Math.random() * 100 + 30) : 0)
                    .careerBestBowlingWickets((int)(Math.random() * 6 + 1))
                    .careerBestBowlingRuns((int)(Math.random() * 50 + 20))
                    .build());
        }
    }

    private void seedMatches() {
        List<Team> teams = teamRepository.findAll();
        if (teams.size() < 4) return;

        Team ind = teams.get(0);
        Team aus = teams.get(1);
        Team eng = teams.get(2);
        Team pak = teams.get(3);
        Team nz  = teams.get(4);
        Team sa  = teams.get(5);

        CreateMatchRequest m1 = CreateMatchRequest.builder()
                .title("IND vs AUS - ICC World Cup Final")
                .team1Id(ind.getId()).team2Id(aus.getId())
                .format(MatchFormat.T20)
                .venue("Narendra Modi Stadium, Ahmedabad")
                .scheduledAt(LocalDateTime.now().minusHours(2))
                .tossWinner("IND").tossDecision("BAT")
                .build();
        var match1 = matchService.createMatch(m1);
        matchService.startMatch(match1.getId());

        CreateMatchRequest m2 = CreateMatchRequest.builder()
                .title("ENG vs PAK - Champions Trophy")
                .team1Id(eng.getId()).team2Id(pak.getId())
                .format(MatchFormat.ODI)
                .venue("Lord's Cricket Ground, London")
                .scheduledAt(LocalDateTime.now().plusDays(1))
                .tossWinner("PAK").tossDecision("BOWL")
                .build();
        matchService.createMatch(m2);

        CreateMatchRequest m3 = CreateMatchRequest.builder()
                .title("NZ vs SA - ODI Series 2nd Match")
                .team1Id(nz.getId()).team2Id(sa.getId())
                .format(MatchFormat.ODI)
                .venue("Eden Park, Auckland")
                .scheduledAt(LocalDateTime.now().minusHours(3))
                .tossWinner("SA").tossDecision("BAT")
                .build();
        var match3 = matchService.createMatch(m3);
        matchService.startMatch(match3.getId());

        CreateMatchRequest m4 = CreateMatchRequest.builder()
                .title("IND vs ENG - Test Series 1st Test")
                .team1Id(ind.getId()).team2Id(eng.getId())
                .format(MatchFormat.TEST)
                .venue("Wankhede Stadium, Mumbai")
                .scheduledAt(LocalDateTime.now().plusDays(3))
                .build();
        matchService.createMatch(m4);

        CreateMatchRequest m5 = CreateMatchRequest.builder()
                .title("AUS vs PAK - T20 Series")
                .team1Id(aus.getId()).team2Id(pak.getId())
                .format(MatchFormat.T20)
                .venue("MCG, Melbourne")
                .scheduledAt(LocalDateTime.now().plusDays(2))
                .tossWinner("AUS").tossDecision("BOWL")
                .build();
        matchService.createMatch(m5);

        log.info("Matches seeded successfully");
    }
}
