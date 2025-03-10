
package trivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trivia.game.Game;
import trivia.game.Player;
import trivia.game.QuestionTheme;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing 'Game' class for...")
public class GameTest {
	@Test
	public void caracterizationTest() {
		// runs 10.000 "random" games to see the output of old and new code mathces
		for (int seed = 1; seed < 10_000; seed++) {
			testSeed(seed, false);
		}
	}

	private void testSeed(int seed, boolean printExpected) {
		String expectedOutput = extractOutput(new Random(seed), new GameOld());
		if (printExpected) {
			System.out.println(expectedOutput);
		}
		String actualOutput = extractOutput(new Random(seed), new Game());
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	@Disabled("enable back and set a particular seed to see the output")
	public void oneSeed() {
		testSeed(1, true);
	}

	private String extractOutput(Random rand, IGame aGame) {
		PrintStream old = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PrintStream inmemory = new PrintStream(baos)) {
			// WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
			System.setOut(inmemory);

			aGame.addPlayer("Chet");
			aGame.addPlayer("Pat");
			aGame.addPlayer("Sue");

			boolean notAWinner = false;
			do {
				aGame.roll(rand.nextInt(5) + 1);

				if (rand.nextInt(9) == 7) {
					notAWinner = aGame.wrongAnswer();
				} else {
					notAWinner = aGame.handleCorrectAnswer();
				}

			} while (notAWinner);
		} finally {
			System.setOut(old);
		}

		return new String(baos.toByteArray());
	}

	@Test
	@DisplayName("Player class generation")
	public void testPlayer(){
		Player p1 = new Player("p1");

		assertEquals("p1", p1.getName());
		assertEquals(0, p1.getPlace());
		assertEquals(0, p1.getPurse());
		assertFalse(p1.isInPenaltyBox());
		assertEquals("p1", p1.toString());
	}

	@Test
	@DisplayName("Theme questions generation")
	public void TestThemeQuestionsGeneration() {
		for(QuestionTheme theme : QuestionTheme.values()) {
			List<String> questions = theme.generateThemeQuestions();

			for(int i = 0; i < questions.size(); i++) {
				assertEquals(theme.getText() + " Question " + i, questions.get(i));
			}
		}
	}

	@Test
	@DisplayName("Game cells generation")
	public void TestGameCellsGeneration() {
		var cells = Game.generateCells(12);
		var expectedCells = new QuestionTheme[] {
				QuestionTheme.POP,
				QuestionTheme.SCIENCE,
				QuestionTheme.SPORTS,
				QuestionTheme.ROCK,
                QuestionTheme.GEOGRAPHY,
				QuestionTheme.POP,
				QuestionTheme.SCIENCE,
				QuestionTheme.SPORTS,
				QuestionTheme.ROCK,
                QuestionTheme.GEOGRAPHY,
				QuestionTheme.POP,
				QuestionTheme.SCIENCE
		};

		for(int i = 0; i < expectedCells.length; i++) {
			assertEquals(expectedCells[i], cells[i]);
		}
	}

	@Test
	@DisplayName("Add player")
	public void TestAddPlayer() {
		Game game = new Game();

		PrintStream old = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PrintStream inmemory = new PrintStream(baos)) {
			// WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
			System.setOut(inmemory);
			game.addPlayer("p1");
			game.addPlayer("p2");
            game.addPlayer("p3");
		} finally {
			System.setOut(old);
		}

		String output = baos.toString();
        String expected = "p1 was added" + System.lineSeparator() +
                "They are player number 1" + System.lineSeparator() +
                "p2 was added" + System.lineSeparator() +
                "They are player number 2" + System.lineSeparator() +
                "p3 was added" + System.lineSeparator() +
                "They are player number 3" + System.lineSeparator();

		assertEquals(expected, output);

        var players = game.getPlayers();
        assertEquals(3, players.size());
        assertEquals(players.next().getName(), "p1");
        assertEquals(players.next().getName(), "p2");
        assertEquals(players.next().getName(), "p3");
	}

    @Test
    @DisplayName("Wrong answer")
    public void TestWrongAnswer() {
        Game game = new Game();

        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream inmemory = new PrintStream(baos)) {
            // WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
            System.setOut(inmemory);
            game.addPlayer("p1");
            game.addPlayer("p2");
            game.wrongAnswer();
        } finally {
            System.setOut(old);
        }

        String output = baos.toString();
        String expected = "p1 was added" + System.lineSeparator() +
                "They are player number 1" + System.lineSeparator() +
                "p2 was added" + System.lineSeparator() +
                "They are player number 2" + System.lineSeparator() +
                "Question was incorrectly answered" + System.lineSeparator() +
                "p1 was sent to the penalty box" + System.lineSeparator();
        assertEquals(expected, output);

        var players = game.getPlayers();
        assertEquals(2, players.size());
        assertFalse(players.next().isInPenaltyBox());
        assertTrue(players.next().isInPenaltyBox());
    }

    @Test
    @DisplayName("Players can't have the same name")
    public void TestPlayersCanNotHaveSameName() {
        Game game = new Game();
        game.addPlayer("p1");
        assertTrue(game.addPlayer("p2"));
        assertTrue(game.addPlayer("p3"));
        assertTrue(game.addPlayer("p4"));

        assertFalse(game.addPlayer("p1"));
        assertFalse(game.addPlayer("p2"));
        assertFalse(game.addPlayer("p3"));
        assertFalse(game.addPlayer("p4"));
    }
}
