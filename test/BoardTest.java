import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

  private Board board;
  private Player alice;
  private Player bob;

  @BeforeEach
  void setUp() {
    board = new Board();
    alice = new Player("Alice", "X");
    bob = new Player("Bob", "O");
  }

  @Test
  void boardStartsWithAllCellsEmpty() {
    for (int row = 0; row < board.getSize(); row++) {
      for (int col = 0; col < board.getSize(); col++) {
        assertTrue(board.isCellEmpty(row, col), "expected cell (" + row + "," + col + ") to be empty");
      }
    }
  }

  @Test
  void makeMovePlacesSymbolAndPreventsOverwrite() {
    assertTrue(board.makeMove(0, 0, alice), "first move should succeed");
    assertFalse(board.isCellEmpty(0, 0), "cell should no longer be empty after move");

    assertFalse(board.makeMove(0, 0, bob), "occupied cell should reject additional moves");
    assertTrue(board.makeMove(0, 1, bob), "different empty cell should accept move");
  }

  @Test
  void makeMoveRejectsOutOfBoundsCoordinates() {
    assertFalse(board.makeMove(-1, 0, alice), "negative row index must be rejected");
    assertFalse(board.makeMove(0, 3, alice), "column beyond board size must be rejected");
    assertFalse(board.makeMove(3, 3, alice), "row beyond board size must be rejected");
  }

  @Test
  void makeMoveRejectsNullPlayer() {
    assertFalse(board.makeMove(1, 1, null), "null player should not be accepted");
    assertTrue(board.isCellEmpty(1, 1), "cell should remain empty when move is rejected");
  }

  @Test
  void checkWinDetectsRowVictory() {
    board.makeMove(0, 0, alice);
    board.makeMove(0, 1, alice);
    board.makeMove(0, 2, alice);

    assertEquals("X", board.checkWin(), "row filled by Alice should yield her symbol");
  }

  @Test
  void checkWinDetectsDiagonalVictory() {
    board.makeMove(0, 0, bob);
    board.makeMove(1, 1, bob);
    board.makeMove(2, 2, bob);

    assertEquals("O", board.checkWin(), "diagonal filled by Bob should yield his symbol");
  }

  @Test
  void resetClearsBoard() {
    board.makeMove(1, 1, alice);
    board.makeMove(0, 2, bob);

    board.reset();

    for (int row = 0; row < board.getSize(); row++) {
      for (int col = 0; col < board.getSize(); col++) {
        assertTrue(board.isCellEmpty(row, col), "reset should clear cell (" + row + "," + col + ")");
      }
    }
  }

  @Test
  void isFullReturnsTrueWhenBoardFilled() {
    for (int row = 0; row < board.getSize(); row++) {
      for (int col = 0; col < board.getSize(); col++) {
        Player current = ((row + col) % 2 == 0) ? alice : bob;
        assertTrue(board.makeMove(row, col, current), "all assignments should succeed for empty board");
      }
    }

    assertTrue(board.isFull(), "board should report full after all cells filled");
  }
}
