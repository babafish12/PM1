/**
 * Represents a Tic-Tac-Toe game board.
 * The board maintains a grid of cells and provides methods to manage game state.
 */
public class Board {

  /** The grid containing all cells on the board. */
  private Cell[] grid;

  /**
   * Constructs a new Board and initializes the grid.
   */
  public Board() {}

  /**
   * Resets the board to its initial state, clearing all cells.
   */
  public void reset() {
    // TODO: Implement
  }

  /**
   * Attempts to make a move on the board at the specified position.
   *
   * @param row the row index of the move
   * @param col the column index of the move
   * @param p   the player making the move
   * @return {@code true} if the move was successful, {@code false} otherwise
   */
  public boolean makeMove(int row, int col, Player p) {
    // TODO: Implement method
    return false;
  }

  /**
   * Checks whether the board is completely filled with moves.
   *
   * @return {@code true} if all cells are occupied, {@code false} otherwise
   */
  public boolean isFull() {
    // TODO: Implement
    return false;
  }

  /**
   * Checks the current board state for a winning condition.
   *
   * @return the symbol of the winning player, or an empty string if no winner
   */
  public String checkWin() {
    // TODO: Implement
    return "";
  }

  /**
   * Displays the current state of the board.
   */
  public void display() {
    // TODO: Implement
  }
}
