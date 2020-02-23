import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorldCup {

    public static void main(String[] args) throws IOException {

        List<Match> matches = getMatches();
//        System.out.println(matches);

        // Zad 1
        List<Match> matchesTill39 = matches.stream()
                .filter((Match m) -> {
                    return m.year <= 1939;
                })
                .collect(Collectors.toList());
//        System.out.println(matchesTill39);

        // Zad 2
        List<Match> matchesInXXI = matches.stream()
                .filter((Match m) -> {
                    return m.year > 2000;
                })
                .collect(Collectors.toList());
//        System.out.println(matchesInXXI);

        // Zad 3
        List<Match> matchesInBerlin = matches.stream()
                .filter((Match m) -> {
                    return m.city.equals("Berlin");
                })
                .collect(Collectors.toList());
//        System.out.println(matchesInBerlin);

        // Zad 4
        List<Match> plMatches = matches.stream()
                .filter((Match m) -> {
                    return m.awayTeam.equals("Poland") || m.homeTeam.equals("Poland");
                })
                .collect(Collectors.toList());
//        System.out.println(plMatches);

        // Zad 5
        List<Match> moreThan40kAudience = matches.stream()
                .filter((Match m) -> {
                    return m.audience > 40000;
                })
                .collect(Collectors.toList());
//        System.out.println(moreThan40kAudience);

        // Zad 6
        List<Match> semiFinalsMatches = matches.stream()
                .filter((Match m) -> {
                    return m.level.equals("Semi-finals");
                })
                .collect(Collectors.toList());
        System.out.println(semiFinalsMatches);
    }

    public static List<Match> getMatches() throws IOException {
        Path path = Paths.get("C:\\Users\\barba\\IdeaProjects\\worldCup\\resources\\wordlcup_v2.csv");

        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);


            List<Match> matches = lines.stream()
                    .map(line -> line.split(";"))
                    .map(WorldCup::matchTransformer)
                    .collect(Collectors.toList());

            return matches;
        }
        return new ArrayList<>();
    }

    public static Match matchTransformer(String[] data) {
        Match match = new Match();
        match.year = Integer.parseInt(validData(data[0]));
        match.city = validData(data[4]);
        match.country = validData(data[5]);
        match.audience = Integer.parseInt(validData(data[10]));
        match.level = validData(data[2]);
        match.homeTeam = validData(data[5]);
        match.awayTeam = validData(data[9]);
        return match;
    }

    public static String validData(String data) {
        if (data.isEmpty()) {
            return "0";
        } else {
            return data;
        }
    }

    static class Match {
        int year;
        String city;
        String country;
        int audience;
        String level;
        String homeTeam;
        String awayTeam;

        public Match() {
        }

        public Match(int year, String city, String country, int audience, String level, String homeTeam, String awayTeam) {
            this.year = year;
            this.city = city;
            this.country = country;
            this.audience = audience;
            this.level = level;
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getAudience() {
            return audience;
        }

        public void setAudience(int audience) {
            this.audience = audience;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getHomeTeam() {
            return homeTeam;
        }

        public void setHomeTeam(String homeTeam) {
            this.homeTeam = homeTeam;
        }

        public String getAwayTeam() {
            return awayTeam;
        }

        public void setAwayTeam(String awayTeam) {
            this.awayTeam = awayTeam;
        }

        @Override
        public String toString() {
            return "Match{" +
                    "year=" + year +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", audience=" + audience +
                    ", level='" + level + '\'' +
                    "}\n";
        }
    }
}
