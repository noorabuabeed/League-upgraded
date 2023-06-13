import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LeagueManager {

    private List<Match> matches;
    Team[] team  ;
    LinkedList<Player> thePlayers ;



    public List<Match> findMatchesByTeam(int teamId) {
        return matches
                      .stream()
                      .filter(match -> match.getHomeTeam().getId() == teamId || match.getAwayTeam().getId() == teamId)
                      .toList();
    }


    public  List<Team> findTopScoringTeams(int n){
        Team TheBigst = null ;
        LinkedList<Team> theResult =new LinkedList<>();
        int counter = 0 , len = team.length ;
        for (int i = 0; i < team.length; i++) {
            for (int j = 0; j <team.length ; j++) {
                if(team[i].getTheGoals().size() >= team[j].getTheGoals().size()){
                    counter++ ;
                }
                if (counter >=len ){
                    theResult.add(team[i]);
                    i = 0 ;
                    len -- ;
                }
            }

        }
        return theResult ;
    }


    public LinkedList<Player> findPlayersWithAtLeastNGoals(int n){
        return (LinkedList<Player>) thePlayers.stream().filter(player -> okey(player , n))
                .toList();


    } //3
    public boolean okey (Player player , int n){
        boolean flag = false ;
        if (player.getTheGoals().size() >= n){
            flag = true ;
        }
        return flag ;
    }   //for helping method 3


    public Team getTeamByPosition(int position){
        return team[position];
    } //4

    public HashMap<Integer, Integer> getTopScorer(int n, HashMap<Integer, Integer> goals) {
        return goals.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    } //5
    public  void callToRead () throws IOException {
     //   LinkedList <String> lines = readFromTheFile("Team.CSV");
//        String [] memo = new String[2];
//        for (int i = 0; i < lines.size(); i++) {
//           memo = lines.get(i).split(",") ;
//           team[i].setName(memo[1]);
//           team[i].setId(Integer.parseInt(memo[2]));
        //this.team = putInTeam(lines);


    }

    public static void printRoster(List<String> roster) {    //تقريبا ما لهي فايده
        for (int i = 0; i < roster.size(); i++) {
            System.out.println((i + 1) + ". " + roster.get(i));
        }
    }


    public static List<Team> makeTeams(File file)throws IOException {

        List<Team> teams = new ArrayList<>();
        BufferedReader bufferedReader = null ;
        FileReader fileReader = null ;
        String line = null ;

        try {
            fileReader = new FileReader(file) ;
            bufferedReader = new BufferedReader(fileReader);

            do {
                line = bufferedReader.readLine();

                if (line != null){

                    String[] parts = line.split(",",2);
                    String teamId = parts[0];
                    String teamName = parts[1];

                    Team team = new Team(teamName, Integer.parseInt(teamId) ,playersInTeams());
                    teams.add(team);
                }

            }while (line != null);
        }finally {
            if (bufferedReader != null)
                bufferedReader.close();
            if (fileReader != null)
                fileReader.close();
        }
        return teams ;
    }

    public static List<String> playersInTeams() {
        String[] firstNames = {
                "John", "Michael", "Emily", "Jessica", "David", "Daniel", "Sarah", "Jennifer", "Matthew", "Andrew",
                "Elizabeth", "Christopher", "William", "Alex", "Olivia", "Emma", "Sophia", "Daniel", "Lucas", "Ava",
                "Liam", "Mia", "Ethan", "Abigail", "Noah", "Isabella", "Benjamin", "Grace", "James", "Charlotte"
        };
        String[] lastNames = {
                "Smith", "Johnson", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas",
                "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez",
                "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "King", "Scott", "Turner", "Adams"
        };

        Random random = new Random();
        List<String> players = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String playerName = firstName + " " + lastName;
            players.add(playerName);
        }

        return players;
    }
    public void generateLeagueSchedule(List<Team> teams) {
        int numTeams = teams.size();
        int numRounds = 9;
        int matchesPerRound = numTeams / 2;

        matches = new ArrayList<>(); // Initialize the 'matches' list

        for (int round = 1; round <= numRounds; round++) {
            System.out.println("Round " + round + " schedule:");

            // Create a copy of the teams list to shuffle the order
            List<Team> shuffledTeams = new ArrayList<>(teams);
            Collections.shuffle(shuffledTeams);

            for (int match = 0; match < matchesPerRound; match++) {
                Team homeTeam = shuffledTeams.get(match);
                Team awayTeam = shuffledTeams.get(match + matchesPerRound);

                Match newMatch = new Match();
                newMatch.setHomeTeam(homeTeam);
                newMatch.setAwayTeam(awayTeam);
                matches.add(newMatch); // Add the match to the 'matches' list

                System.out.println(homeTeam.getName() + " vs " + awayTeam.getName());
            }

            System.out.println();
        }
    }

    public void simulateLeagueMatches() throws InterruptedException {
        if (matches == null || matches.isEmpty()) {
            System.out.println("No matches found. Please generate the league schedule.");
            return;
        }

        for (Match match : matches) {
            Team homeTeam = match.getHomeTeam();
            Team awayTeam = match.getAwayTeam();

            System.out.println("Match: " + homeTeam.getName() + " vs " + awayTeam.getName());

            // Simulate the match by generating random home and away goals
            int homeGoals = simulateGoals();
            int awayGoals = simulateGoals();

            match.setHomeGoals(homeGoals);
            match.setAwayGoals(awayGoals);

            System.out.println("Result: " + homeTeam.getName() + " " + homeGoals + " - " + awayGoals + " " + awayTeam.getName());
            System.out.println();

            // Simulate a delay of 10 seconds for the next match
            Thread.sleep(10000);
        }
    }

    private int simulateGoals() {
        // Generate a random number of goals between 0 and 5
        return (int) (Math.random() * 6);
    }

    public void calculateTeamStandings(List<Team> teams) {
        List<Team> aTeams = teams;

        // Calculate points and goal difference for each team
        for (Team team : teams) {
            int points = 0;
            int goalDifference = 0;

            for (Match match : matches) {
                if (match.getHomeTeam().equals(team)) {
                    int homeGoals = match.getHomeGoals();
                    int awayGoals = match.getAwayGoals();
                    int goalDiff = homeGoals - awayGoals;

                    if (homeGoals > awayGoals) {
                        points += 3; // Home team won
                    } else if (homeGoals == awayGoals) {
                        points += 1; // Draw
                    }

                    goalDifference += goalDiff;
                } else if (match.getAwayTeam().equals(team)) {
                    int homeGoals = match.getHomeGoals();
                    int awayGoals = match.getAwayGoals();
                    int goalDiff = awayGoals - homeGoals;

                    if (awayGoals > homeGoals) {
                        points += 3; // Away team won
                    } else if (homeGoals == awayGoals) {
                        points += 1; // Draw
                    }

                    goalDifference += goalDiff;
                }
            }

            team.setPoints(points);
            team.setGoalDifference(goalDifference);
        }

        // Sort teams based on points and goal difference
        Collections.sort(teams, Comparator.comparing(Team::getPoints)
                .thenComparing(Team::getGoalDifference).reversed());

        // Print the team standings
        System.out.println("Team Standings:");
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println((i + 1) + ". " + team.getName() + " - Points: " + team.getPoints() +
                    ", Goal Difference: " + team.getGoalDifference());
        }
    }






}












