
package trivia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

		assertEquals("p1", p1.name);
		assertEquals(0, p1.place);
		assertEquals(0, p1.purse);
		assertFalse(p1.inPenaltyBox);
		assertEquals("p1", p1.toString());
	}

	@Test
	@DisplayName("Theme questions generation")
	public void TestThemeQuestionsGeneration() {
		for(Game.QuestionTheme theme : Game.QuestionTheme.values()) {
			List<String> questions = theme.generateThemeQuestions();

			for(int i = 0; i < questions.size(); i++) {
				assertEquals(theme.getText() + " Question " + i, questions.get(i));
			}
		}
	}
}
