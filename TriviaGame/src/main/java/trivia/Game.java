package trivia;

import java.util.*;

// REFACTOR ME
public class Game implements IGame {
   ArrayList<String> players = new ArrayList<>();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

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
   }

   public void generateThemeQuestions(QuestionTheme theme) {
      var themeQuestions = new LinkedList<String>();
      this.questions.put(theme, themeQuestions);

      for (int i = 0; i < 50; i++) {
         themeQuestions.add(theme.name() + " Question " + i);
      }
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      places[howManyPlayers()] = 1;
      purses[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;
      players.add(playerName);

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

      if (inPenaltyBox[currentPlayer]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 12) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer)
                               + "'s new location is "
                               + places[currentPlayer]);
            System.out.println("The category is " + currentCategory().name());
            askQuestion();
         } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 12) places[currentPlayer] = places[currentPlayer] - 12;

         System.out.println(players.get(currentPlayer)
                            + "'s new location is "
                            + places[currentPlayer]);
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
      if (places[currentPlayer] - 1 == 0) return QuestionTheme.Pop;
      if (places[currentPlayer] - 1 == 4) return QuestionTheme.Pop;
      if (places[currentPlayer] - 1 == 8) return QuestionTheme.Pop;
      if (places[currentPlayer] - 1 == 1) return QuestionTheme.Science;
      if (places[currentPlayer] - 1 == 5) return QuestionTheme.Science;
      if (places[currentPlayer] - 1 == 9) return QuestionTheme.Science;
      if (places[currentPlayer] - 1 == 2) return QuestionTheme.Sports;
      if (places[currentPlayer] - 1 == 6) return QuestionTheme.Sports;
      if (places[currentPlayer] - 1 == 10) return QuestionTheme.Sports;
      return QuestionTheme.Rock;
   }

   public boolean handleCorrectAnswer() {
      if (inPenaltyBox[currentPlayer]) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was current!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer)
                               + " now has "
                               + purses[currentPlayer]
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
         purses[currentPlayer]++;
         System.out.println(players.get(currentPlayer)
                            + " now has "
                            + purses[currentPlayer]
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
