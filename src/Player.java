/**
 * Represents a player in a game.
 * Each player has a name and a symbol (for example "X" or "O").
 */
public class Player {

    /** The name of the player. */
    private String name;

    /** The symbol associated with the player (e.g., "X" or "O"). */
    private String symbol;

    /**
     * Creates a new Player with the specified name and symbol.
     *
     * @param name   the name of the player
     * @param symbol the symbol representing the player
     */
    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
