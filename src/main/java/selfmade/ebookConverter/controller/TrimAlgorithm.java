package selfmade.ebookConverter.controller;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import selfmade.ebookConverter.model.TextColour;

import java.util.ArrayList;
import java.util.List;

public class TrimAlgorithm {

    private String title = "";
    private String type = "";
    private String endMark = "";
    private String vocabulary = "";
    private ObservableList<Node> contentList;

    private List<String> texts = new ArrayList<>();
    private List<String> textSeparated = new ArrayList<>();

    private StringBuilder textToAdd = new StringBuilder();

    public TrimAlgorithm(ObservableList<Node> content) {

        this.contentList = content;

    }

    //Format Button to designation and save it to List
    public void getContentToTextFragments() {
        for (Node node : contentList) {
            ToggleButton toggleButton = (ToggleButton) node;
            String buttonText = toggleButton.getText();
            texts.add(buttonText);
        }
        //findAndPrintSpecialEntries();
        getMarkings();
    }

    private void findAndPrintSpecialEntries() {
        /*for (String text : texts) {
            if (text.length() >= 2 && !Character.isLetterOrDigit(text.charAt(0)) && !Character.isLetterOrDigit(text.charAt(text.length() - 1))) {
                System.out.println(text);
            }
        }
         */

    }

    //Search content for begin/end marks
    private void getMarkings() {
        System.out.println("GetMarking wurde aufgerufen");
        for (Node node : contentList) {
            ToggleButton toggleButton = (ToggleButton) node;
            String buttonText = toggleButton.getText();
            String styleString = node.lookup(".toggle-button").getStyle();
            switch (styleString) {
                case "-fx-background-color: #D9FFD9":
                    endMark = buttonText;//text before endMark is added as unit
                    System.out.println(endMark);
                case "-fx-background-color: #FFD9D9":
                    vocabulary = buttonText;  //word that is later translated
                    System.out.println(vocabulary);
                case "-fx-background-color: #FFFFD9":
                    title = buttonText;//Only include books with this title
                    System.out.println(title);
                case "-fx-background-color: #D9E5FF":
                    type = buttonText;//Only include text with this type
                    System.out.println(type);

            }
        }
            extractVocabulary();
    }

    private void extractVocabulary() {
        System.out.println("You arte heere");
    }

    private void separatTextFragments(String value) {
        for (String text : texts) {
            if (text.equals(value)) {
            }
                 /*
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


            for (int i = 0; i < texts.size() ; i++) {
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

             */
        }
    }

}
