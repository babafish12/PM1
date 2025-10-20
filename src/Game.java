import java.util.Scanner;

/**
 * Controls the flow of a Tic-Tac-Toe match.
 * <p>
 * The {@code Game} class keeps references to the {@link Board}, the two {@link Player} objects,
 * and the internationalization helper {@link I18N}. It handles switching turns, validating moves,
 * and announcing the result at the end of the match.
 * </p>
 */
public class Game {
    /** Playing field used for the current match. */
    private Board board;
    /** Player who is currently taking a turn. */
    private Player currentPlayer;
    /** Player using the first symbol, usually X. */
    private Player player1;
    /** Player using the second symbol, usually O. */
    private Player player2;
    /** Stores the winner after the game ends, or {@code null} for a draw. */
    private Player winner;
    /** Internationalization helper used for localized messages. */
    private I18N i18n;
    /** Shared scanner instance used to read console input. */
    private Scanner scanner;

    /**
     * Creates a new game with two players, an internationalization helper, and an input scanner.
     *
     * @param player1 the first player (typically using symbol "X")
     * @param player2 the second player (typically using symbol "O")
     * @param i18n    helper object used to print localized text
     * @param scanner shared scanner used to read user input
     */
    public Game(Player player1, Player player2, I18N i18n, Scanner scanner) {
        this.player1 = player1;
        this.player2 = player2;
        this.i18n = i18n;
        this.scanner = (scanner != null) ? scanner : new Scanner(System.in);
    }

    /**
     * Starts the game by initializing the board and running the game loop.
     */
    public void start() {
        this.board = new Board();
        this.board.reset();
        this.currentPlayer = player1;
        this.winner = null;

        System.out.println(i18n.printVocab("welcome"));
        board.display();

        while (true) {
            System.out.println(String.format(i18n.printVocab("player_turn"), currentPlayer.getName()));
            String input = getInput();

            if (!validateInput(input)) {
                System.out.println(i18n.printVocab("invalid_move"));
                continue;
            }

            String[] parts = input.split(",");
            int row = Integer.parseInt(parts[0].trim()) - 1;
            int col = Integer.parseInt(parts[1].trim()) - 1;

            if (!board.makeMove(row, col, currentPlayer)) {
                System.out.println(i18n.printVocab("invalid_move"));
                continue;
            }

            board.display();

            String winningSymbol = board.checkWin();
            if (!winningSymbol.isEmpty()) {
                winner = winningSymbol.equals(player1.getSymbol()) ? player1 : player2;
                break;
            }

            if (board.isFull()) {
                break;
            }

            switchPlayer();
        }

        if (winner != null) {
            System.out.println(String.format(i18n.printVocab("winner"), winner.getName()));
        } else {
            System.out.println(i18n.printVocab("draw"));
        }
        System.out.println(i18n.printVocab("goodbye"));
    }

    /**
     * Switches the current player after a successful move.
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * Checks whether the game is over either by a win or a draw.
     *
     * @return {@code true} if the game has finished; otherwise {@code false}
     */
    public boolean isOver() {
        if (board == null) {
            return false;
        }
        if (winner != null) {
            return true;
        }
        String winningSymbol = board.checkWin();
        if (!winningSymbol.isEmpty()) {
            winner = winningSymbol.equals(player1.getSymbol()) ? player1 : player2;
            return true;
        }
        return board.isFull();
    }

    /**
     * Returns the winner of the game, if any.
     *
     * @return the winner or {@code null} when the match ended in a draw
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Reads input from the current player.
     *
     * @return the raw input string (e.g., {@code "1,2"})
     */
    public String getInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Validates the user input.
     * <p>A valid input follows the pattern {@code row,col} where row and column are integers
     * between 1 and the current board size.</p>
     *
     * @param input raw input entered by the user
     * @return {@code true} if the input is valid; otherwise {@code false}
     */
    public boolean validateInput(String input) {
        if (board == null) {
            return false;
        }
        if (input == null || input.isEmpty()) {
            return false;
        }
        String[] parts = input.split(",");
        if (parts.length != 2) {
            return false;
        }
        try {
            int row = Integer.parseInt(parts[0].trim());
            int col = Integer.parseInt(parts[1].trim());
            int size = board.getSize();
            return row >= 1 && row <= size && col >= 1 && col <= size && board.isCellEmpty(row - 1, col - 1);
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
