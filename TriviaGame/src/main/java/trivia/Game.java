package trivia;

import java.util.*;

// REFACTOR ME
public class Game implements IGame {

   ArrayList<Player> players = new ArrayList<>();
   QuestionTheme[] cells;
   Map<QuestionTheme, LinkedList<String>> questions = new HashMap<>();
   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public enum QuestionTheme {
      Pop,
      Science,
      Sports,
      Rock
   }

   public Game() {
      for(QuestionTheme theme : QuestionTheme.values()) {
         generateThemeQuestions(theme);
      }

      generateCells(12);
   }

   public void generateThemeQuestions(QuestionTheme theme) {
      var themeQuestions = new LinkedList<String>();
      this.questions.put(theme, themeQuestions);

      for (int i = 0; i < 50; i++) {
         themeQuestions.add(theme.name() + " Question " + i);
      }
   }

   public void generateCells(int cellCount) {
      cells = new QuestionTheme[cellCount];
      var themes = QuestionTheme.values();

      int themeIndex = 0;
      for (int i = 0; i < cellCount; i++) {
         cells[i] = themes[themeIndex++];
         themeIndex %= themes.length;
      }
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(players.get(currentPlayer) + " is the current player");
      System.out.println("They have rolled a " + roll);
      
      Player player = players.get(currentPlayer);

      if (player.inPenaltyBox) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

            player.place = player.place + roll;
            if (player.place > cells.length) player.place = player.place - cells.length;

            System.out.println(players.get(currentPlayer)
                               + "'s new location is "
                               + player.place);
            System.out.println("The category is " + currentCategory().name());
            askQuestion();
         } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         player.place = player.place + roll;
         if (player.place > cells.length) player.place = player.place - cells.length;

         System.out.println(players.get(currentPlayer)
                            + "'s new location is "
                            + player.place);
         System.out.println("The category is " + currentCategory().name());
         askQuestion();
      }

   }

   private void askQuestion() {
      QuestionTheme currentTheme = currentCategory();
      LinkedList<String> themeQuestions = questions.get(currentTheme);

      if(themeQuestions.isEmpty()) {
         generateThemeQuestions(currentTheme);
      }

      System.out.println(themeQuestions.removeFirst());
   }


   private QuestionTheme currentCategory() {
      return cells[places[currentPlayer] - 1];
   }

   public boolean handleCorrectAnswer() {

      Player player = players.get(currentPlayer);

      if (player.inPenaltyBox) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was current!!!!");
            player.purse++;
            System.out.println(players.get(currentPlayer)
                               + " now has "
                               + player.purse
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
         } else {
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return true;
         }


      } else {

         System.out.println("Answer was current!!!!");
         player.purse++;
         System.out.println(players.get(currentPlayer)
                            + " now has "
                            + player.purse
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      Player player = players.get(currentPlayer);
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      player.inPenaltyBox = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      Player player = players.get(currentPlayer);
      return !(player.purse == 6);
   }
}
