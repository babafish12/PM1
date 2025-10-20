/**
 * Die Klasse {@code Game} steuert den gesamten Spielfluss des Tic-Tac-Toe-Spiels.
 * <p>
 * Sie verwaltet das Spielfeld ({@link Board}), die Spieler ({@link Player}) sowie den aktuellen Spielzustand.
 * Die Klasse koordiniert die Züge der Spieler, überprüft, ob das Spiel beendet ist, und bestimmt den Gewinner.
 * Zudem interagiert sie mit der Klasse {@code I18N}, um sprachabhängige Ausgaben anzuzeigen.
 * </p>
 *
 * <p><b>Gespeicherte Informationen:</b> Referenzen auf das Spielfeld, den aktuellen Spieler sowie beide Spielerobjekte.</p>
 *
 * @author team02
 * @version 1.0
 */
public class Game {
    private Board board;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    /**
     * Erstellt ein neues {@code Game}-Objekt mit zwei Spielern.
     *
     * @param player1 der erste Spieler (z. B. mit Symbol "X")
     * @param player2 der zweite Spieler (z. B. mit Symbol "O")
     */
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Startet das Spiel, initialisiert das Spielfeld
     * und legt den ersten Spieler fest.
     */
    public void start() {
        // TODO: Implementierung hinzufügen
    }

    /**
     * Wechselt den aktuellen Spieler nach jedem Zug.
     */
    public void switchPlayer() {
        // TODO: Implementierung hinzufügen
    }

    /**
     * Überprüft, ob das Spiel beendet ist,
     * z. B. durch Sieg oder Unentschieden.
     *
     * @return {@code true}, wenn das Spiel vorbei ist; sonst {@code false}
     */
    public boolean isOver() {
        return false;
    }

    /**
     * Gibt den Gewinner des Spiels zurück, falls vorhanden.
     *
     * @return der Gewinner oder {@code null}, falls es keinen gibt
     */
    public Player getWinner() {
        return null;
    }

    /**
     * Fordert eine Eingabe vom aktuellen Spieler an,
     * z. B. die gewünschte Position auf dem Spielfeld.
     *
     * @return der eingegebene String (z. B. "1,2")
     */
    public String getInput() {
        return null;
    }

    /**
     * Überprüft, ob die vom Spieler eingegebene Eingabe gültig ist.
     * <p>
     * Eine gültige Eingabe muss im Spielfeld liegen und darf kein bereits belegtes Feld betreffen.
     * </p>
     *
     * @return {@code true}, wenn die Eingabe gültig ist; sonst {@code false}
     */
    public boolean validateInput() {
        return false;
    }
}
