package selfmade.ebookConverter.controller;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class TrimAlgorithm {


    private ObservableList<Node> contentList;

    private List<String> texts = new ArrayList<>();
    private List<String> textSeparated = new ArrayList<>();


    public TrimAlgorithm(ObservableList<Node> content) {
        this.contentList = content;
    }

    public void getTextToVocabulary() {
        for (Node node : contentList) {
            String styleString = node.lookup(".toggle-button").getStyle();
            if (styleString.equals("-fx-background-color: #D9FFD9")) {
                ToggleButton button = (ToggleButton) node;
                String buttonText = button.getText();
                texts.add(buttonText);
                separatTextFragments(buttonText);
            }
        }
    }

    private void separatTextFragments(String value) {
        for (String text : texts) {
           /* if (text.equals(value)) {
                // Wenn die aktuelle Zeile die Trennzeile ist, fügen Sie den gespeicherten Text zur Liste hinzu
                if (!textSeparated.isEmpty()) {
                    System.out.println("Arrived");
                    String savedText = String.join("\n", textSeparated); // Die Texte mit Zeilenumbruch verbinden
                    System.out.println("Gespeicherter Text: \n" + savedText);
                    textSeparated.clear(); // Liste leeren, um für den nächsten gespeicherten Text bereit zu sein
                }
            } else {
                // Wenn die aktuelle Zeile kein Trennzeichen ist, fügen Sie sie zur Liste hinzu
                System.out.println(text);
                textSeparated.add(text);
            }
            for (String texter : textSeparated) {
                System.out.println(text);
            }
        }
            */
            for (int i = 0; i < texts.size(); i++) {
                String currentText = textSeparated.get(i);
                if (currentText.contains(value)) {
                    // Wenn der Wert gefunden wurde, fügen Sie den vorherigen Textbaustein (falls vorhanden) zur extrahierten Liste hinzu.
                    if (i > 0) {
                        String previousText = texts.get(i - 1);
                        textSeparated.add(previousText);
                    }
                }
            }
            for (String extractedText : textSeparated) {
                System.out.println(extractedText);
            }
        }
    }

}
