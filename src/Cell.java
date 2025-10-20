/**
 * Represents a single cell in a 2D grid, such as a Tic-Tac-Toe board.
 * A cell is identified by its row and column index and may contain a value (e.g., "X" or "O").
 */
public class Cell {
    /** The row index of this cell in the grid. */
    private int row;

    /** The column index of this cell in the grid. */
    private int col;

    /** The value stored in this cell (empty string if not set). */
    private String value;

    /**
     * Constructs a Cell with a given row and column index.
     * The cell value is initialized as empty.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = "";
    }

    /**
     * Checks whether the cell is empty (contains no value).
     *
     * @return {@code true} if the value is an empty string, {@code false} otherwise
     */
    public boolean isEmpty() {
        return this.value.equals("");
    }

    /**
     * Sets the value of this cell.
     *
     * @param value the new value to store (e.g., "X", "O")
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the current value of this cell.
     *
     * @return the cell value as a String
     */
    public String getValue() {
        return this.value;
    }
}

