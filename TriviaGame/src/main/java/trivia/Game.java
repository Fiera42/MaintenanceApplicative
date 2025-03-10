package trivia;

import java.util.*;

public class Game implements IGame {

   ArrayList<Player> players = new ArrayList<>();
   QuestionTheme[] cells;
   Map<QuestionTheme, LinkedList<String>> questions = new HashMap<>();
   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public enum QuestionTheme {
      POP("Pop"),
      SCIENCE("Science"),
      SPORTS("Sports"),
      ROCK("Rock");

      private final String text;

      QuestionTheme(String text) {
         this.text = text;
      }

      public String getText() {
         return text;
      }

      public LinkedList<String> generateThemeQuestions() {
         var themeQuestions = new LinkedList<String>();

         for (int i = 0; i < 50; i++) {
            themeQuestions.add(this.text + " Question " + i);
         }

         return themeQuestions;
      }
   }

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
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public void roll(int roll) {
      var player = players.get(this.currentPlayer);

      System.out.println(player + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (player.inPenaltyBox) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(player + " is getting out of the penalty box");
         }
         else {
            System.out.println(player + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
            return;
         }
      }

      player.place = player.place + roll;
      player.place %= cells.length;

      System.out.println(player
              + "'s new location is "
              + (player.place+1));
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
      return cells[players.get(currentPlayer).place];
   }

   public boolean handleCorrectAnswer() {
      Player player = players.get(currentPlayer);

      if(player.inPenaltyBox && !isGettingOutOfPenaltyBox) {
         currentPlayer++;
         currentPlayer %= players.size();
         return true;
      }

      System.out.println("Answer was correct!!!!");
      player.purse++;
      System.out.println(players.get(currentPlayer)
              + " now has "
              + player.purse
              + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      currentPlayer %= players.size();

      return winner;
   }

   public boolean wrongAnswer() {
      Player player = players.get(currentPlayer);
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      player.inPenaltyBox = true;

      currentPlayer++;
      currentPlayer %= players.size();
      return true;
   }

   private boolean didPlayerWin() {
      Player player = players.get(currentPlayer);
      return !(player.purse == 6);
   }
}
