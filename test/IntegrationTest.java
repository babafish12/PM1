import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * Consolidated integration scenarios that exercise the interaction between the console entry point
 * and the main gameplay loop, including localization and draw handling.
 */
public class IntegrationTest {

  /**
   * Plays a full match containing invalid inputs and verifies that the game loop keeps prompting
   * until it receives valid moves before declaring the winner.
   */
  @Test
  void gameRetriesUntilValidMoveAndDeclaresWinner() {
    Player alice = new Player("Alice", "X");
    Player bob = new Player("Bob", "O");
    I18N i18n = new I18N("English", "en");
    String moves =
        String.join(
                "\n",
                "bad",
                "0,0",
                "1,1",
                "1,1",
                "1,2",
                "2,2",
                "2,3",
                "3,3")
            + "\n";
    Scanner scanner = new Scanner(new ByteArrayInputStream(moves.getBytes(StandardCharsets.UTF_8)));
    Game game = new Game(alice, bob, i18n, scanner);

    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(buffer, true));
    try {
      game.start();
    } finally {
      System.setOut(originalOut);
    }

    String output = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    long invalidCount =
        output
            .lines()
            .filter(line -> line.contains("This move is not allowed. Try again."))
            .count();

    assertTrue(invalidCount >= 3, "expected at least three invalid move notices");
    assertEquals(alice, game.getWinner(), "Alice should win via the main diagonal");
    assertTrue(game.isOver(), "game should be reported as finished");
    assertTrue(output.contains("Winner: Alice"), "winner announcement missing");
  }

  /**
   * Plays a full game that results in a draw and verifies that the outcome is reported correctly to
   * the players.
   */
  @Test
  void gameEndsInDrawAndAnnouncesDraw() {
    Player alice = new Player("Alice", "X");
    Player bob = new Player("Bob", "O");
    I18N i18n = new I18N("English", "en");
    String moves =
        String.join(
                "\n",
                "1,1",
                "1,2",
                "1,3",
                "2,3",
                "2,1",
                "3,1",
                "2,2",
                "3,3",
                "3,2")
            + "\n";
    Scanner scanner = new Scanner(new ByteArrayInputStream(moves.getBytes(StandardCharsets.UTF_8)));
    Game game = new Game(alice, bob, i18n, scanner);

    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(buffer, true));
    try {
      game.start();
    } finally {
      System.setOut(originalOut);
    }

    String output = new String(buffer.toByteArray(), StandardCharsets.UTF_8);

    assertTrue(game.isOver(), "game should be finished");
    assertNull(game.getWinner(), "draw should leave winner unset");
    assertTrue(output.contains("The game ended in a draw."), "draw message should be printed");
  }

  /**
   * Ensures that players can switch to Azerbaijani, play through a full match, and receive localized
   * output including the correct winner announcement.
   */
  @Test
  void mainAllowsFullGameInAzerbaijani() {
    String output =
        runMainWithInput(
            "az",
            "Ali",
            "Leyla",
            "1,1",
            "1,2",
            "2,2",
            "2,1",
            "3,3");

    assertTrue(output.contains("Xos geldiniz: Tic-Tac-Toe!"), "welcome message missing");
    assertTrue(output.contains("Dil deyisdirildi."), "language switch confirmation missing");
    assertTrue(output.contains("Ali, xanani secin (setr,sutun):"), "player prompt missing");
    assertTrue(output.contains("Qalib: Ali"), "winner message missing");
    assertTrue(output.contains("Oynadiginiz ucun teshekkurler!"), "goodbye message missing");
  }

  /**
   * Verifies that an unsupported language request falls back to English, anonymous players use
   * default names, and the match can finish in a draw with the correct summary text.
   */
  @Test
  void mainFallsBackToEnglishAndHandlesDraw() {
    String output =
        runMainWithInput(
            "de",
            "",
            "",
            "1,1",
            "1,2",
            "1,3",
            "2,3",
            "2,1",
            "3,1",
            "2,2",
            "3,3",
            "3,2");

    assertTrue(
        output.contains("Language not supported. Falling back to English."), "fallback not reported");
    assertTrue(
        output.contains("Player 1, choose a cell (row,col):"), "default player prompt missing");
    assertTrue(output.contains("The game ended in a draw."), "draw message missing");
    assertTrue(output.contains("Thank you for playing!"), "goodbye message missing");
  }

  /**
   * Executes the {@link Main#main(String[])} entry point with the provided console input and returns
   * the resulting console output.
   *
   * @param lines lines that should be fed into {@code System.in}
   * @return full console output emitted during the execution
   */
  private String runMainWithInput(String... lines) {
    String joined = String.join("\n", lines);
    if (!joined.endsWith("\n")) {
      joined = joined + "\n";
    }
    ByteArrayInputStream input =
        new ByteArrayInputStream(joined.getBytes(StandardCharsets.UTF_8));

    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    PrintStream capture = new PrintStream(buffer, true);

    InputStream originalIn = System.in;
    PrintStream originalOut = System.out;
    System.setIn(input);
    System.setOut(capture);
    try {
      Main.main(new String[0]);
    } finally {
      System.setIn(originalIn);
      System.setOut(originalOut);
    }

    return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
  }
}
