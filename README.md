# Cricket Score Management System

A scalable, real-time cricket scoring platform built with **Spring Boot 3.2**, **Java 21/26**, **Spring Data JPA**, **H2 Database**, and a responsive frontend dashboard powered by **Server-Sent Events (SSE)**.

---

## 🌟 Key Features

1. **Real-Time Live Scoring Engine:**
   - Background delivery simulation (`LiveScoreSimulator`) running every 3 seconds per live match.
   - Realistic delivery outcomes: dots, singles, doubles, boundaries (4s, 6s), extras (wides, no-balls), and dismissals (bowled, caught, LBW, run-out, stumped).
   - Dynamic over management, strike rotation, and innings transitions.

2. **Server-Sent Events (SSE) Streaming:**
   - Real-time score pushes without client polling (`/api/matches/stream/global` and `/api/matches/{id}/stream`).

3. **RESTful API Suite:**
   - Full CRUD endpoints for matches, teams, and players.
   - Ball-by-ball score submissions and match lifecycle controls.
   - Detailed scorecard breakdowns and commentary feeds.

4. **Interactive Dashboard:**
   - Dark-mode responsive UI located at `src/main/resources/static/index.html`.
   - Real-time score ticker, live commentary feed, batting/bowling scorecard tabs, team standings, and player directory.

---

## 📁 Project Structure

```
cricket-score-system/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/cricket/
│   │   │   ├── CricketScoreApplication.java
│   │   │   ├── config/
│   │   │   │   ├── DataSeeder.java
│   │   │   │   └── WebConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── MatchController.java
│   │   │   │   ├── PlayerController.java
│   │   │   │   └── TeamController.java
│   │   │   ├── dto/
│   │   │   │   ├── CreateMatchRequest.java
│   │   │   │   ├── MatchDTO.java
│   │   │   │   ├── PlayerDTO.java
│   │   │   │   ├── PlayerMatchStatDTO.java
│   │   │   │   ├── ScoreUpdateDTO.java
│   │   │   │   ├── ScoreUpdateRequest.java
│   │   │   │   └── TeamDTO.java
│   │   │   ├── model/
│   │   │   │   ├── Match.java
│   │   │   │   ├── MatchFormat.java
│   │   │   │   ├── MatchStatus.java
│   │   │   │   ├── Player.java
│   │   │   │   ├── PlayerMatchStat.java
│   │   │   │   ├── ScoreUpdate.java
│   │   │   │   └── Team.java
│   │   │   ├── repository/
│   │   │   │   ├── MatchRepository.java
│   │   │   │   ├── PlayerMatchStatRepository.java
│   │   │   │   ├── PlayerRepository.java
│   │   │   │   ├── ScoreUpdateRepository.java
│   │   │   │   └── TeamRepository.java
│   │   │   └── service/
│   │   │       ├── LiveScoreSimulator.java
│   │   │       ├── MatchService.java
│   │   │       ├── PlayerService.java
│   │   │       ├── SseEmitterService.java
│   │   │       └── TeamService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           └── index.html
│   └── test/
│       └── java/com/cricket/
│           └── CricketScoreApplicationTests.java
```

---

## 🚀 How to Run

### Option 1: Using the Pre-built JAR
```bash
java -jar target/cricket-score-system-1.0.0.jar
```

### Option 2: Using Maven
```bash
mvn spring-boot:run
```

Once started, open:
- **Dashboard:** [http://localhost:8080](http://localhost:8080)
- **H2 Database Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:cricketdb`)
- **Actuator Health:** [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

