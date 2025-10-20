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
    }

    /**
     * Switches the current language of the I18N system.
     *
     * @param languageCode The new language code (e.g., "en" or "az").
     * @return {@code true} if the language was switched successfully, {@code false} otherwise.
     */
    public boolean switchLanguage(String languageCode) {
        // TODO: Implement logic to switch languages
        return false;
    }

    /**
     * Returns the translated text for the given key based on the current language setting.
     *
     * @param key The vocabulary key (e.g., "greet", "exit", etc.).
     * @return The translated string if available; otherwise, the key itself.
     */
    public String printVocab(String key) {
        // TODO: Implement logic to return translation from correct HashMap
        return key;
    }
}

