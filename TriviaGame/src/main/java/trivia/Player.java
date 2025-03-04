package trivia;

public class Player{

    protected String name;
    protected int place;
    protected int purse;
    protected boolean inPenaltyBox; 

    public Player(String name){
        this.name = name;
        this.place = 0;
        this.purse = 0;
        this.inPenaltyBox = false;
    }

    public String toString(){
        return name;
    }

}