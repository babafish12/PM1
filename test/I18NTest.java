import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class I18NTest {

  @Test
  void defaultsToEnglishVocabulary() {
    I18N i18n = new I18N("English", "en");

    assertEquals("English", i18n.getLanguage());
    assertEquals("en", i18n.getLanguageCode());
    assertEquals("Welcome to Tic-Tac-Toe!", i18n.printVocab("welcome"));
  }

  @Test
  void switchLanguageUpdatesLanguageAndVocabulary() {
    I18N i18n = new I18N("English", "en");

    assertTrue(i18n.switchLanguage("az"), "switching to Azerbaijani should succeed");
    assertEquals("Azerbaijani", i18n.getLanguage());
    assertEquals("az", i18n.getLanguageCode());
    assertEquals("Xos geldiniz: Tic-Tac-Toe!", i18n.printVocab("welcome"));
  }

  @Test
  void switchLanguageRejectsUnsupportedCode() {
    I18N i18n = new I18N("English", "en");

    assertFalse(i18n.switchLanguage("de"), "unsupported language should be rejected");
    assertEquals("English", i18n.getLanguage(), "language should stay unchanged after failure");
    assertEquals("en", i18n.getLanguageCode(), "language code should stay unchanged after failure");
  }

  @Test
  void printVocabFallsBackToEnglishWhenMissingInActiveLanguage() {
    I18N i18n = new I18N("English", "en");
    i18n.enVocab.put("custom_key", "Hello!");
    i18n.switchLanguage("az");

    assertEquals("Hello!", i18n.printVocab("custom_key"), "should fall back to English text");
  }

  @Test
  void printVocabReturnsKeyWhenMissingEverywhere() {
    I18N i18n = new I18N("English", "en");

    assertEquals("unknown_key", i18n.printVocab("unknown_key"));
  }
}
