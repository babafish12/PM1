# Testkonzept  
**Projekt:** Tic-Tac-Toe  
**Team:** Team02 – Azerbaijan Technology  
**Modul:** IT.PM1 – Softwareprojekt 1  
**Version:** 1.6  
**Datum:** 15.10.2025  

---

## 1. Einführung  

Dieses Testkonzept beschreibt die Strategie, den Umfang und die Durchführung der Tests für das Softwareprojekt *Tic-Tac-Toe*.  
Ziel ist es, sicherzustellen, dass das Programm funktional korrekt, robust und benutzerfreundlich ist.  
Der Fokus liegt dabei auf einer systematischen, aber pragmatischen Teststrategie mit klarer Priorisierung der relevanten Funktionalitäten.

---

## 2. Testziele und Umfang

Das Ziel der Tests ist es, sicherzustellen, dass:
- die Spiellogik jederzeit korrekt funktioniert (Züge, Gewinnerkennung, Unentschieden),
- Benutzereingaben korrekt validiert werden,
- Sprachumschaltung (Internationalisierung) konsistent arbeitet,
- und das Zusammenspiel der Klassen stabil bleibt.

Nicht getestet werden grafische oder performanzrelevante Aspekte, da diese für das Projekt nicht zentral sind.

---

## 3. Teststrategie

Wir unterscheiden vier Testebenen:

| Ebene | Ziel | Art | Besonderheit |
|--------|------|-----|--------------|
| **Unit-Tests** | Prüfung einzelner Methoden und Klassen | automatisiert (JUnit 5) | Alle Kernmethoden (`checkWin`, `isDraw`, `validateInput`, etc.) werden isoliert getestet. |
| **Integrationstests** | Zusammenspiel zwischen den Klassen (`Game`, `Board`, `Player`, `I18N`) | automatisiert (JUnit 5) | Integrationstests werden **immer von einer anderen Person als dem Code-Autor** entwickelt. |
| **Systemtests** | Vollständiger Spielablauf von Start bis Ende | manuell & teils automatisiert | Dienen der End-to-End-Validierung des Gesamtsystems. |
| **Akzeptanztests** | Überprüfung aus Nutzersicht | manuell | Fokus auf Verständlichkeit und Nutzererlebnis. |

### Automatisierungsstrategie
- Unit- und Integrationstests werden bei jedem Commit ausgeführt (lokal oder CI).
- Manuelle Tests werden in einer Testtabelle dokumentiert.
- Bei Änderungen an der Spiellogik erfolgt ein Re-Test aller betroffenen Bereiche.

---

## 4. Verantwortlichkeiten

| Rolle | Verantwortlichkeit | Begründung |
|--------|--------------------|-------------|
| **Entwickler** | Implementierung der Unit-Tests | Kennt den Code am besten und kann gezielt testen. |
| **Anderes Teammitglied** | Erstellung der Integrationstests | Sichert Unabhängigkeit und Objektivität. |
| **QA-Teammitglied** | Durchführung der System- und Akzeptanztests | Fokus auf Benutzerfluss und Gesamtfunktionalität. |

---

## 5. Testzeitpunkte

| Zeitpunkt | Beschreibung | Ziel |
|------------|---------------|------|
| **Während der Implementierung** | Unit-Tests werden parallel (testgetrieben) erstellt. | Frühe Fehlererkennung. |
| **Nach jedem signifikanten Code-Commit** | Integrationstests werden ausgeführt. | Sicherstellung funktionaler Zusammenarbeit. |
| **Vor der Abgabe** | System- und Akzeptanztests. | Gesamtprüfung auf Vollständigkeit und Stabilität. |

---

## 6. Testfokus (Was ist wichtig?)

Anstatt alle möglichen Kombinationen zu testen, konzentrieren wir uns auf die essenziellen Fälle, bei denen Fehler wahrscheinlich und kritisch wären.

| Bereich | Warum wichtig | Testart |
|----------|----------------|----------|
| **Gewinnerkennung (`checkWin`)** | Kritisch für Spielende; leicht fehleranfällig (Reihen, Spalten, Diagonalen). | Unit & Integration |
| **Unentschieden (`isDraw`)** | Muss korrekt zwischen laufendem Spiel, Sieg und Unentschieden unterscheiden. | Unit |
| **Eingabevalidierung (`validateInput`)** | Muss fehlerhafte Benutzereingaben erkennen, um Spielabbruch zu vermeiden. | Unit |
| **Sprachumschaltung (`I18N`)** | Nutzerfreundlichkeit, Fehlertoleranz bei mehrfacher Umschaltung. | Unit & Integration |
| **Spielablauf (Zugfolge, Spielerwechsel, Reset)** | Basis des Spielflusses, häufige Fehlerquelle. | System |

---

## 7. Manuelle Tests

Einige Tests lassen sich nicht oder nur schwer automatisieren, z. B.:
- Spielablauf mit zwei realen Spielern (Nutzerfeedback).
- Sprachumschaltung in laufendem Spiel (visuelle Kontrolle).
- Verhalten bei mehrfachen Resets oder ungewöhnlichen Zügen.

Diese Tests werden manuell dokumentiert (Testdatum, Tester, Ergebnis, Bemerkung).

---

## 8. Testdaten

- **Spieler X:** Symbol "X", Name "Alice"  
- **Spieler O:** Symbol "O", Name "Bob"  
- **Sprachen:** "EN" (Standard), "AZ" (alternative Sprache)  
- **Eingaben:** `"0,0"`, `"1,2"`, `"abc"`, `"9,9"`  
- **Spezialfälle:** volle Felder (Unentschieden), gewinnende Reihen/Spalten/Diagonalen  

---

## 9. Testumgebung

| Komponente | Beschreibung |
|-------------|---------------|
| **IDE** | IntelliJ IDEA / Neovim / Cursor (AI-Mode disabled) |
| **Java-Version** | 17 oder höher |
| **Testframework** | JUnit 5 |
| **Betriebssystem** | Windows / Linux / macOS |

---

## 10. Abnahmekriterien

- Alle Unit- und Integrationstests bestehen fehlerfrei.  
- Keine kritischen Fehler im Systemtest.  
- Sprachwechsel funktioniert konsistent (auch bei EN→EN / AZ→AZ).  
- Eingabevalidierung reagiert korrekt auf fehlerhafte Eingaben.  
- Spielende wird korrekt erkannt (8 Gewinnbedingungen + Unentschieden).  

---

## 11. Risiken und Gegenmaßnahmen

| Risiko | Auswirkung | Gegenmaßnahme |
|--------|-------------|----------------|
| **Fehlerhafte Spiellogik** | Falsche Spielergebnisse | Automatisierte Tests aller Gewinnfälle |
| **Unvollständige Übersetzungen** | Fehlende Texte / Fehlerausgabe | Prüfung aller Keys in I18N |
| **Fehlerhafte Eingabevalidierung** | Absturz oder fehlerhafte Züge | Tests mit gültigen & ungültigen Eingaben |
| **Nicht getestete Randfälle** | Unerkannte Fehler | Erweiterte Integrationstests durch zweite Person |

---

## 12. Fazit

Dieses Testkonzept definiert eine klare, aber pragmatische Teststrategie mit Fokus auf die wichtigsten funktionalen Aspekte des Spiels.  
Integrationstests werden gezielt von anderen Teammitgliedern entwickelt, um Objektivität zu sichern.  
Nicht jede Methode wird einzeln dokumentiert, sondern die wesentlichen Testziele werden definiert.  
Durch die Kombination aus automatisierten und manuellen Tests wird eine hohe Softwarequalität und Nachvollziehbarkeit sichergestellt.  
