/**
 * Represents a Tic-Tac-Toe game board.
 * The board maintains a grid of cells and provides methods to manage game state.
 */
public class Board {

  /** Default board size (3×3 grid). */
  private static final int SIZE = 3;

  /** The grid containing all cells on the board. */
  private Cell[] grid;

  /**
   * Constructs a new Board and initializes the grid.
   */
  public Board() {
    this.grid = new Cell[SIZE * SIZE];
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        grid[index(row, col)] = new Cell(row, col);
      }
    }
  }

  /**
   * Resets the board to its initial state, clearing all cells.
   */
  public void reset() {
    for (Cell cell : grid) {
      cell.setValue("");
    }
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
    if (!isInside(row, col) || p == null) {
      return false;
    }

    Cell target = grid[index(row, col)];
    if (!target.isEmpty()) {
      return false;
    }

    target.setValue(p.getSymbol());
    return true;
  }

  /**
   * Checks whether the board is completely filled with moves.
   *
   * @return {@code true} if all cells are occupied, {@code false} otherwise
   */
  public boolean isFull() {
    for (Cell cell : grid) {
      if (cell.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks the current board state for a winning condition.
   *
   * @return the symbol of the winning player, or an empty string if no winner
   */
  public String checkWin() {
    // Check rows and columns
    for (int i = 0; i < SIZE; i++) {
      String rowWinner = checkLine(index(i, 0), index(i, 1), index(i, 2));
      if (!rowWinner.isEmpty()) {
        return rowWinner;
      }

      String colWinner = checkLine(index(0, i), index(1, i), index(2, i));
      if (!colWinner.isEmpty()) {
        return colWinner;
      }
    }

    // Check diagonals
    String diagonalOne = checkLine(index(0, 0), index(1, 1), index(2, 2));
    if (!diagonalOne.isEmpty()) {
      return diagonalOne;
    }

    String diagonalTwo = checkLine(index(0, 2), index(1, 1), index(2, 0));
    if (!diagonalTwo.isEmpty()) {
      return diagonalTwo;
    }

    return "";
  }

  /**
   * Displays the current state of the board.
   */
  public void display() {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        String value = grid[index(row, col)].getValue();
        if (value.isEmpty()) {
          value = " ";
        }
        System.out.print(" " + value + " ");
        if (col < SIZE - 1) {
          System.out.print("|");
        }
      }
      System.out.println();
      if (row < SIZE - 1) {
        System.out.println("---+---+---");
      }
    }
  }

  /**
   * Returns the board size.
   *
   * @return the size of the board (number of rows/columns)
   */
  public int getSize() {
    return SIZE;
  }

  /**
   * Checks whether the specified cell is empty.
   *
   * @param row zero-based row index
   * @param col zero-based column index
   * @return {@code true} if the cell is empty, {@code false} otherwise
   */
  public boolean isCellEmpty(int row, int col) {
    if (!isInside(row, col)) {
      return false;
    }
    return grid[index(row, col)].isEmpty();
  }

  /**
   * Computes the index of a cell in the one-dimensional grid array.
   *
   * @param row zero-based row index
   * @param col zero-based column index
   * @return the index in the grid array
   */
  private int index(int row, int col) {
    return row * SIZE + col;
  }

  /**
   * Checks whether three cells contain the same non-empty value.
   *
   * @param first  index of the first cell
   * @param second index of the second cell
   * @param third  index of the third cell
   * @return the winning symbol if all three cells match, otherwise an empty string
   */
  private String checkLine(int first, int second, int third) {
    String firstValue = grid[first].getValue();
    if (firstValue.isEmpty()) {
      return "";
    }
    if (firstValue.equals(grid[second].getValue()) && firstValue.equals(grid[third].getValue())) {
      return firstValue;
    }
    return "";
  }

  /**
   * Checks if the given coordinates are inside the board.
   *
   * @param row zero-based row index
   * @param col zero-based column index
   * @return {@code true} if the coordinates are valid, otherwise {@code false}
   */
  private boolean isInside(int row, int col) {
    return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
  }
}
