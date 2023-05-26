import java.awt.*;
import java.util.LinkedList;

public class Team {
    private int id ;
    private String name ;
    private  LinkedList<Player> PlayerInTheTeam  ;
    private LinkedList<Match> theMatchs  ;
    private LinkedList<Goal> theGoals ;

    public LinkedList<Match> getTheMatchs (){
        return theMatchs ;
    }
    public LinkedList<Goal> getTheGoals(){
        return theGoals ;
    }

}
