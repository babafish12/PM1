import java.util.HashMap;

/**
 * The {@code I18N} class provides a simple internationalization system
 * that allows switching between multiple languages and retrieving
 * localized vocabulary entries based on keys.
 *
 * <p>This class supports English and Azerbaijani translations by default,
 * stored in two {@link HashMap} objects.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * I18N i18n = new I18N("English", "en");
 * i18n.enVocab.put("greet", "Hello");
 * i18n.azVocab.put("greet", "Salam");
 *
 * i18n.switchLanguage("az");
 * System.out.println(i18n.printVocab("greet")); // prints "Salam"
 * </pre>
 *
 * @author David
 * @version 1.0
 */
public class I18N {
    /** The name of the current language (e.g., "English", "Azerbaijani"). */
    private String language;

    /** The ISO language code for the current language (e.g., "en", "az"). */
    private String languageCode;

    /** Vocabulary map containing English translations (key → text). */
    HashMap<String, String> enVocab;

    /** Vocabulary map containing Azerbaijani translations (key → text). */
    HashMap<String, String> azVocab;

    /**
     * Constructs a new {@code I18N} instance with the given language and language code.
     *
     * @param language      The name of the language.
     * @param languageCode  The ISO code representing the language (e.g., "en", "az").
     */
    public I18N(String language, String languageCode) {
        this.language = language;
        this.languageCode = languageCode;
        this.enVocab = new HashMap<>();
        this.azVocab = new HashMap<>();
        loadDefaultVocabulary();
    }

    /**
     * Switches the current language of the I18N system.
     *
     * @param languageCode The new language code (e.g., "en" or "az").
     * @return {@code true} if the language was switched successfully, {@code false} otherwise.
     */
    public boolean switchLanguage(String languageCode) {
        if ("en".equalsIgnoreCase(languageCode)) {
            this.language = "English";
            this.languageCode = "en";
            return true;
        } else if ("az".equalsIgnoreCase(languageCode)) {
            this.language = "Azerbaijani";
            this.languageCode = "az";
            return true;
        }
        return false;
    }

    /**
     * Returns the translated text for the given key based on the current language setting.
     *
     * @param key The vocabulary key (e.g., "greet", "exit", etc.).
     * @return The translated string if available; otherwise, the key itself.
     */
    public String printVocab(String key) {
        HashMap<String, String> activeMap = "az".equals(languageCode) ? azVocab : enVocab;
        String value = activeMap.get(key);
        if (value == null) {
            // Fall back to English before returning the key itself
            value = enVocab.get(key);
        }
        return value != null ? value : key;
    }

    /**
     * Returns the current language name.
     *
     * @return the language name as a {@code String}
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the current language code.
     *
     * @return the language code (e.g., {@code "en"})
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Populates the vocabulary maps with default texts used by the application.
     */
    private void loadDefaultVocabulary() {
        enVocab.put("welcome", "Welcome to Tic-Tac-Toe!");
        enVocab.put("choose_language", "Choose language (en/az):");
        enVocab.put("language_changed", "Language switched.");
        enVocab.put("language_error", "Language not supported. Falling back to English.");
        enVocab.put("player_one_name", "Enter name for player one:");
        enVocab.put("player_two_name", "Enter name for player two:");
        enVocab.put("player_turn", "%s, choose a cell (row,col):");
        enVocab.put("invalid_move", "This move is not allowed. Try again.");
        enVocab.put("winner", "Winner: %s");
        enVocab.put("draw", "The game ended in a draw.");
        enVocab.put("goodbye", "Thank you for playing!");

        azVocab.put("welcome", "Xos geldiniz: Tic-Tac-Toe!");
        azVocab.put("choose_language", "Dili secin (en/az):");
        azVocab.put("language_changed", "Dil deyisdirildi.");
        azVocab.put("language_error", "Dil desteklenmir. Ingilis diline qayidildi.");
        azVocab.put("player_one_name", "Birinci oyunchunun adini daxil edin:");
        azVocab.put("player_two_name", "Ikinci oyunchunun adini daxil edin:");
        azVocab.put("player_turn", "%s, xanani secin (setr,sutun):");
        azVocab.put("invalid_move", "Bu gedise icaze verilmir. Yeniden cehd edin.");
        azVocab.put("winner", "Qalib: %s");
        azVocab.put("draw", "Oyun hech-heche bitdi.");
        azVocab.put("goodbye", "Oynadiginiz ucun teshekkurler!");
    }
}
