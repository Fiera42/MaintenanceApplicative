import oldApp.OldMain;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test if new app similar to old app")
public class GolderMasterTest {

    @Test
    @DisplayName("Golder master")
    public void caracterizationTest() throws IOException {
        // runs 10.000 "random" games to see the output of old and new code mathces
        for (int seed = 1; seed < 10; seed++) {
            testSeed(seed, false);
        }
    }

    private void testSeed(int seed, boolean printExpected) throws IOException {
        String expectedOutput = extractOutput(new Random(seed), OldMain::main);
        if (printExpected) {
            System.out.println(expectedOutput);
        }
        String actualOutput = extractOutput(new Random(seed), Main::main);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @Disabled("enable back and set a particular seed to see the output")
    public void oneSeed() throws IOException {
        testSeed(1, true);
    }

    private String extractOutput(Random random, Consumer<String[]> main) throws IOException {
        PrintStream oldOutput = System.out;
        InputStream oldInput = System.in;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (
                PrintStream inMemoryOutput = new PrintStream(baos);
                PipedOutputStream pipedOut = new PipedOutputStream();
                PipedInputStream pipedIn = new PipedInputStream(pipedOut);
                PrintWriter writer = new PrintWriter(pipedOut, true)
        ) {
            System.setOut(inMemoryOutput);
            System.setIn(pipedIn);

            new Thread(() -> {
                try {
                    main.accept(new String[]{});
                } catch (NoSuchElementException e) {
                    // This error is thrown because we stop feeding new lines and it just cries
                }
            }).start();

            writer.println("2");
            writer.println("Fiera");
            writer.println("666");
            writer.println("666");
            writer.println("5");
            writer.println("O");
            writer.println("2");
            writer.println("Fiera");
            writer.println("Gerard");
            writer.println("Gégé94`; DROP TABLE Users;");
            writer.println("Gégé94`; DROP TABLE Users;");
            writer.println("5");
            writer.println("O");
            writer.println("1");
            writer.println("Fiera");
            writer.println("666");

            int rndValue;
            for(int i = 0; i < 500; i++) {
                rndValue = (i % 2 == 0) ? random.nextInt(100) : random.nextInt(6);
                if(rndValue == 5) rndValue++;
                writer.println("" + rndValue);
            }

            for(int i = 0; i < 10; i++) {
                writer.println("1");
            }


            Thread.sleep(100);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            // Restore original streams
            System.setOut(oldOutput);
            System.setIn(oldInput);
        }

        return baos.toString();
    }
}
