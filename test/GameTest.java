import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class GameTest {

  @Test
  void startsGame() {
    // Capture System.out
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    try {
      // Run Main
      Main.main(new String[] {});

      // Check output
      String output = outputStream.toString().trim();
      assertEquals("Hello World!", output);
    } finally {
      // Restore System.out
      System.setOut(originalOut);
    }
  }
}
