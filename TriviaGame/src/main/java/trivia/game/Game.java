package trivia.game;

import trivia.IGame;
import trivia.utils.CircularLinkedList;

import java.util.*;

public class Game implements IGame {

   private final CircularLinkedList<Player> players = new CircularLinkedList<>();
   private final QuestionTheme[] cells;
   private final Map<QuestionTheme, LinkedList<String>> questions = new HashMap<>();
   private boolean isGettingOutOfPenaltyBox;

   public Game() {
      for(QuestionTheme theme : QuestionTheme.values()) {
         this.questions.put(theme, theme.generateThemeQuestions());
      }
      cells = generateCells(12);
   }

   public static QuestionTheme[] generateCells(int cellCount) {
      var res = new QuestionTheme[cellCount];
      var themes = QuestionTheme.values();

      int themeIndex = 0;
      for (int i = 0; i < cellCount; i++) {
         res[i] = themes[themeIndex++];
         themeIndex %= themes.length;
      }

      return res;
   }

   public boolean addPlayer(String playerName) {
      players.addLast(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public void roll(int roll) {
      var player = players.getHead();

      System.out.println(player + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (player.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            player.setInPenaltyBox(false);
            System.out.println(player + " is getting out of the penalty box");
         }
         else {
            System.out.println(player + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
            return;
         }
      }

      player.setPlace(player.getPlace() + roll);
      player.setPlace(player.getPlace() % cells.length);

      System.out.println(player
              + "'s new location is "
              + (player.getPlace() + 1));
      System.out.println("The category is " + currentCategory().getText());
      askQuestion();
   }

   private void askQuestion() {
      QuestionTheme currentTheme = currentCategory();
      LinkedList<String> themeQuestions = questions.get(currentTheme);

      if(themeQuestions.isEmpty()) {
         questions.put(currentTheme, currentTheme.generateThemeQuestions());
      }

      System.out.println(themeQuestions.removeFirst());
   }

   private QuestionTheme currentCategory() {
      return cells[players.getHead().getPlace()];
   }

   public boolean handleCorrectAnswer() {
      Player player = players.getHead();

      if(player.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
         players.next();
         return true;
      }

      System.out.println("Answer was correct!!!!");
      player.setPurse(player.getPurse() + 1);
      System.out.println(players.getHead()
              + " now has "
              + player.getPurse()
              + " Gold Coins.");

      boolean winner = didPlayerWin();
      players.next();

      return winner;
   }

   public boolean wrongAnswer() {
      Player player = players.getHead();
      System.out.println("Question was incorrectly answered");
      System.out.println(players.getHead() + " was sent to the penalty box");
      player.setInPenaltyBox(true);

      players.next();
      return true;
   }

   private boolean didPlayerWin() {
      Player player = players.getHead();
      return !(player.getPurse() == 6);
   }

   public CircularLinkedList<Player> getPlayers() {
      return players;
   }
}
