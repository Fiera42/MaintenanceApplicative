package trivia.game;

public class Player{

    private final String name;
    private int place;
    private int purse;
    private boolean inPenaltyBox;

    public Player(String name){
        this.name = name;
        this.place = 0;
        this.purse = 0;
        this.inPenaltyBox = false;
    }

    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}