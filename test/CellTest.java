import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class CellTest {

  @Test
  void constructorInitializesEmptyValue() {
    Cell cell = new Cell(0, 0);

    assertTrue(cell.isEmpty(), "newly constructed cell should be empty");
    assertEquals("", cell.getValue(), "empty cells should expose blank value");
  }

  @Test
  void setValueStoresSymbol() {
    Cell cell = new Cell(1, 2);
    cell.setValue("X");

    assertFalse(cell.isEmpty(), "cell should no longer be empty after assigning symbol");
    assertEquals("X", cell.getValue(), "stored symbol should be returned verbatim");
  }

  @Test
  void setValueAllowsClearingCell() {
    Cell cell = new Cell(2, 1);
    cell.setValue("O");
    cell.setValue("");

    assertTrue(cell.isEmpty(), "setting an empty string should mark the cell as empty again");
  }
}
