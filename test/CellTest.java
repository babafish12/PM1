import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CellTest {
  @Test
  public void paramsTooSmall() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Cell(0, -1);
    });
  }

  @Test
  public void paramsTooBig() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Cell(4, 5);
    });
  }

  @Test
  public void shouldReturnTrueIfValueIsEmpty() {
    Cell cell = new Cell(3, 1);

    assertEquals(true, cell.isEmpty());
  }

  @Test
  public void shouldReturnFalseIfValueIsNotEmpty() {
    Cell cell = new Cell(3, 1);

    cell.setValue("X");

    assertEquals(false, cell.isEmpty());
  }
}
