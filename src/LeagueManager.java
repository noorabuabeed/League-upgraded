import java.util.LinkedList;

public class LeagueManager {
    Match match = new Match() ;
    Team[] team  ;
    public static void main(String[] args) {


    }

    public LinkedList<Match> findMatchesByTeam(int teamId) {
        return team[teamId].getTheMatchs();
    }


   public  LinkedList<Team> findTopScoringTeams(int n){
        LinkedList<Team> mostGoalsScored ;
//        LinkedList<Goal> TheBigest = team[0].getTheGoals();
        int count = 0;
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               if (team[i].getTheGoals().size() >= team[j].getTheGoals().size() ){
                   count++ ;
               }
               if (count == n) ;
           }

       }
       return null ;
   }


}
