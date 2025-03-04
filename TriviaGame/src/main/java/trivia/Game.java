package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// REFACTOR ME
public class Game implements IGame {
   ArrayList<Player> players = new ArrayList<>();

   public enum QuestionTheme {
      Pop,
      Science,
      Sports,
      Rock
   }

   Map<QuestionTheme, LinkedList<String>> questions = new HashMap<>();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      for(QuestionTheme qt : QuestionTheme.values()) {
         var themeQuestions = new LinkedList<String>();
         this.questions.put(qt, themeQuestions);

         for (int i = 0; i < 50; i++) {
            themeQuestions.add(qt.name() + " Question " + i);
         }
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
            if (player.place > 12) player.place = player.place - 12;

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
         if (player.place > 12) player.place = player.place - 12;

         System.out.println(players.get(currentPlayer)
                            + "'s new location is "
                            + player.place);
         System.out.println("The category is " + currentCategory().name());
         askQuestion();
      }

   }

   private void askQuestion() {
      System.out.println(
              questions.get(currentCategory())
                      .removeFirst()
      );
   }


   private QuestionTheme currentCategory() {
      Player player = players.get(currentPlayer);
      if (player.place - 1 == 0) return QuestionTheme.Pop;
      if (player.place - 1 == 4) return QuestionTheme.Pop;
      if (player.place - 1 == 8) return QuestionTheme.Pop;
      if (player.place - 1 == 1) return QuestionTheme.Science;
      if (player.place - 1 == 5) return QuestionTheme.Science;
      if (player.place - 1 == 9) return QuestionTheme.Science;
      if (player.place - 1 == 2) return QuestionTheme.Sports;
      if (player.place - 1 == 6) return QuestionTheme.Sports;
      if (player.place - 1 == 10) return QuestionTheme.Sports;
      return QuestionTheme.Rock;
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
