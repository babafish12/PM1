import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class GameTest {

  private Scanner scannerForMoves(String moves) {
    return new Scanner(new ByteArrayInputStream(moves.getBytes(StandardCharsets.UTF_8)));
  }

  private void setField(Game game, String fieldName, Object value) {
    try {
      Field field = Game.class.getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(game, value);
    } catch (ReflectiveOperationException exception) {
      throw new RuntimeException(exception);
    }
  }

  private <T> T getField(Game game, String fieldName, Class<T> type) {
    try {
      Field field = Game.class.getDeclaredField(fieldName);
      field.setAccessible(true);
      return type.cast(field.get(game));
    } catch (ReflectiveOperationException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Test
  void startDeclaresWinnerWhenWinningSequenceProvided() {
    Player alice = new Player("Alice", "X");
    Player bob = new Player("Bob", "O");
    I18N i18n = new I18N("English", "en");
    String moves = String.join("\n", "1,1", "1,2", "2,2", "1,3", "3,3") + "\n";
    Game game = new Game(alice, bob, i18n, scannerForMoves(moves));

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(output));
    try {
      game.start();
    } finally {
      System.setOut(originalOut);
    }

    assertSame(alice, game.getWinner());
    assertTrue(game.isOver());
    String console = output.toString();
    assertTrue(console.contains("Winner: Alice"));
    assertTrue(console.contains("Thank you for playing!"));
  }

  @Test
  void startEndsInDrawWhenBoardFillsWithoutWinner() {
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
    Game game = new Game(alice, bob, i18n, scannerForMoves(moves));

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(output));
    try {
      game.start();
    } finally {
      System.setOut(originalOut);
    }

    assertNull(game.getWinner());
    assertTrue(game.isOver());
    String console = output.toString();
    assertTrue(console.contains("The game ended in a draw."));
  }

  @Test
  void validateInputAcceptsCoordinatesForEmptyCell() {
    Player alice = new Player("Alice", "X");
    Player bob = new Player("Bob", "O");
    Game game = new Game(alice, bob, new I18N("English", "en"), scannerForMoves(""));
    Board board = new Board();
    setField(game, "board", board);

    assertTrue(game.validateInput("1,1"));
  }

  @Test
  void validateInputRejectsMoveIntoOccupiedCell() {
    Player alice = new Player("Alice", "X");
    Player bob = new Player("Bob", "O");
    Game game = new Game(alice, bob, new I18N("English", "en"), scannerForMoves(""));
    Board board = new Board();
    board.makeMove(0, 0, alice);
    setField(game, "board", board);

    assertFalse(game.validateInput("1,1"));
  }

  @Test
  void switchPlayerAlternatesBetweenPlayers() {
    Player alice = new Player("Alice", "X");
    Player bob = new Player("Bob", "O");
    Game game = new Game(alice, bob, new I18N("English", "en"), scannerForMoves(""));
    setField(game, "currentPlayer", alice);

    game.switchPlayer();
    assertSame(bob, getField(game, "currentPlayer", Player.class));

    game.switchPlayer();
    assertSame(alice, getField(game, "currentPlayer", Player.class));
  }

  @Test
  void isOverReturnsFalseWhenBoardNotInitialized() {
    Player alice = new Player("Alice", "X");
    Player bob = new Player("Bob", "O");
    Game game = new Game(alice, bob, new I18N("English", "en"), scannerForMoves(""));

    assertFalse(game.isOver());
  }
}
