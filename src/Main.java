import java.util.Scanner;

/**
 * Entry point of the application. Prepares the game configuration and starts the match.
 */
public class Main {

    /**
     * Application entry point. Initializes internationalization, reads player names, and starts the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        I18N i18n = new I18N("English", "en");

        System.out.println(i18n.printVocab("choose_language"));
        String languageChoice = scanner.nextLine().trim();
        if (!languageChoice.isEmpty()) {
            if (i18n.switchLanguage(languageChoice)) {
                System.out.println(i18n.printVocab("language_changed"));
            } else {
                System.out.println(i18n.printVocab("language_error"));
            }
        }

        System.out.println(i18n.printVocab("player_one_name"));
        String playerOneName = scanner.nextLine().trim();
        if (playerOneName.isEmpty()) {
            playerOneName = "Player 1";
        }

        System.out.println(i18n.printVocab("player_two_name"));
        String playerTwoName = scanner.nextLine().trim();
        if (playerTwoName.isEmpty()) {
            playerTwoName = "Player 2";
        }

        Player playerOne = new Player(playerOneName, "X");
        Player playerTwo = new Player(playerTwoName, "O");

        Game game = new Game(playerOne, playerTwo, i18n, scanner);
        game.start();
    }
}
