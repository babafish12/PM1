# team02-azerbaijan-technology-projekt1-tic-tac-toe

![image](https://github.zhaw.ch/user-attachments/assets/65c72b93-c39d-460e-a410-e3928e962b9b)


## Klasse I18N

Die Klasse I18N ist für die Internationalisierung (Sprachunterstützung) der Anwendung zuständig. Sie verwaltet die aktuelle Sprache und die zugehörigen Übersetzungen, die in HashMaps gespeichert sind. Über diese Klasse kann der Textinhalt der Benutzeroberfläche in verschiedenen Sprachen (Englisch und Aserbaidschanisch) ausgegeben werden.
Sie stellt dem Spiel die passenden Übersetzungen bereit und wird von der Klasse Game verwendet, um Texte in der gewählten Sprache anzuzeigen.

Gespeicherte Informationen: aktuelle Sprache, Sprachcode, zwei HashMaps mit Vokabeln.

---

## Klasse Game

Die Klasse Game steuert den gesamten Spielfluss. Sie verwaltet die Spieler, das Spielfeld (Board) und den aktuellen Spielzustand. Außerdem koordiniert sie die Züge der Spieler, überprüft, ob das Spiel beendet ist, und bestimmt den Gewinner.
Game ist die zentrale Steuereinheit der Anwendung und interagiert direkt mit den Klassen Board, Player und indirekt mit Cell. Sie nutzt außerdem I18N, um sprachabhängige Ausgaben zu ermöglichen.

Gespeicherte Informationen: Referenzen auf das Spielfeld, den aktuellen Spieler sowie beide Spielerobjekte.

---

## Klasse Board

Die Klasse Board repräsentiert das Spielfeld des Spiels als 3×3-Gitter von Cell-Objekten. Sie ist für das Zurücksetzen des Spielfelds, die Verarbeitung der Spielzüge und die Überprüfung des Spielstands (z. B. ob jemand gewonnen hat oder das Feld voll ist) verantwortlich.
Board interagiert direkt mit den Cell-Objekten und mit der Game-Klasse, die die Spiellogik steuert.

Gespeicherte Informationen: ein Array von 9 Cell-Objekten.

---

## Klasse Cell

Die Klasse Cell stellt ein einzelnes Feld des Spielfelds dar. Sie speichert die Position (Reihe und Spalte) sowie den aktuellen Wert („X“, „O“ oder leer).
Sie wird ausschließlich vom Board verwaltet, das ihre Zustände liest und verändert, wenn Spieler einen Zug machen.

Gespeicherte Informationen: Zeile, Spalte und gespeicherter Wert.

---

## Klasse Player

Die Klasse Player repräsentiert einen Spieler im Spiel. Sie speichert den Namen des Spielers und das Symbol („X“ oder „O“), mit dem dieser spielt.
Player wird von der Klasse Game genutzt, um die abwechselnden Züge der beiden Spieler zu verwalten.

Gespeicherte Informationen: Name und Symbol des Spielers.

---

## Zusammenarbeit der Klassen

Das Spiel wird durch die Klasse Game gestartet, welche zwei Player-Objekte und ein Board erstellt. Das Board wiederum besteht aus mehreren Cell-Objekten.
Die Game-Klasse verwaltet den Ablauf, fragt über I18N Texte in der richtigen Sprache ab, überprüft über das Board, ob ein Spielzug gültig ist oder das Spiel beendet wurde, und wechselt zwischen den Spielern.
Die Zusammenarbeit kann in einem vereinfachten Ablauf so beschrieben werden:

1. Game initialisiert Board und Spieler.
2. Player führt einen Zug aus → Board übergibt den Zug an die entsprechende Cell.
3. Board überprüft, ob eine Gewinnbedingung erfüllt ist.
4. Game wertet das Ergebnis aus und zeigt über I18N entsprechende Nachrichten an.

