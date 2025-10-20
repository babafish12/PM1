import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test
  void exposesNameAndSymbol() {
    Player player = new Player("Alice", "X");

    assertEquals("Alice", player.getName());
    assertEquals("X", player.getSymbol());
  }

  @Test
  void allowsCustomSymbols() {
    Player player = new Player("Robot", "#");

    assertEquals("#", player.getSymbol());
  }
}
