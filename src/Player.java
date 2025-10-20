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

    /**
     * Returns the player's name.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the symbol used by the player.
     *
     * @return the symbol as a {@code String}
     */
    public String getSymbol() {
        return symbol;
    }
}
