package trivia;

import java.util.*;

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
      System.out.println("The category is " + currentCategory().name());
      askQuestion();
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
