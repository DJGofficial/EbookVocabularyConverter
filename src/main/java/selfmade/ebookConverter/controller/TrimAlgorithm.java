package selfmade.ebookConverter.controller;

import selfmade.ebookConverter.model.EbookViewUIManager;
import selfmade.ebookConverter.view.EbookView;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;

import java.util.*;
import java.util.List;

public class TrimAlgorithm {

    // Variablen zur Speicherung von Informationen
    private String title = "";
    private StringBuilder titleToAdd = new StringBuilder();
    private String type = titleToAdd.toString();
    private String endMark = "";
    private String vocabulary = "";
    private ObservableList<Node> contentList;

    private ArrayList<String> texts = new ArrayList<>();
    private ArrayList<String> entryList = new ArrayList<>();
    private ArrayList<String> trimmedList = new ArrayList<>();
    EbookView ebookView;
    ButtonController buttonController;
    EbookViewUIManager uiManager;

    // Konstruktor für den TrimAlgorithmus
    public TrimAlgorithm(ObservableList<Node> content, EbookView ebookView) {
        this.ebookView = ebookView;
        this.contentList = content;
    }

    // Überprüft, ob EndMark und Vokabel als Mindestanforderung für den Algorithmus markiert sind
    public boolean[] checkContent() {
        boolean hasEndMark = false;
        boolean hasVocabulary = false;
        for (Node node : contentList) {
            String styleString = node.lookup(".toggle-button").getStyle();
            switch (styleString) {
                case "-fx-background-color: #D9FFD9":
                    hasEndMark = true;
                    break;
                case "-fx-background-color: #FFD9D9":
                    hasVocabulary = true;
                    break;
            }
        }
        return new boolean[]{hasEndMark, hasVocabulary};
    }

    // Formatieren des Buttons zur Bezeichnung und Speichern in der Liste
    public void getContentToTextFragments() {
        for (Node node : contentList) {
            ToggleButton toggleButton = (ToggleButton) node;
            String buttonText = toggleButton.getText();
            texts.add(buttonText + " ");
        }
        getMarkings();
    }

    // Sucht den Inhalt nach Anfangs-/Endmarkierungen ab
    public void getMarkings() {
        for (Node node : contentList) {
            ToggleButton toggleButton = (ToggleButton) node;
            String buttonText = toggleButton.getText();
            String styleString = node.lookup(".toggle-button").getStyle();
            switch (styleString) {
                case "-fx-background-color: #D9FFD9":
                    endMark = buttonText.trim(); // Text vor der EndMark wird als Einheit hinzugefügt
                    break;
                case "-fx-background-color: #FFD9D9":
                    vocabulary = buttonText; // Wort, das später übersetzt wird
                    break;
                case "-fx-background-color: #FFFFD9":
                    titleToAdd.append(buttonText + " "); // Nur Bücher mit diesem Titel werden einbezogen
                    System.out.println(titleToAdd);
                    break;
                case "-fx-background-color: #D9E5FF":
                    type = buttonText; // Nur Text mit diesem Typ wird einbezogen
                    break;
            }
        }
        runListDetermineDistance(endMark, vocabulary, type);
    }

    // Speichert Textblöcke basierend auf der Entfernung zwischen EndMark und Vokabel
    private void textBlockStorage(int distanceIndex, String endMark, String title, String type) {
        StringBuilder currentBlock = new StringBuilder();

        String[] deconstructTitle = title.split(" ");

        for (String line : texts) {
            if (line.trim().equals(endMark)) {
                entryList.add(currentBlock.toString());
                currentBlock = new StringBuilder();
            } else {
                currentBlock.append(line).append("\n");
            }
        }

        for (int i = 0; i < entryList.size(); i++) {
            String line = entryList.get(i);

            if (!(title.isEmpty()) && !(type.isEmpty())) {
                if (line.contains(title) && line.contains(type)) {
                    trimmedList.add(entryList.get(i));
                }
            } else if (!title.isEmpty()) {
                if (line.contains(deconstructTitle[0])) {
                    trimmedList.add(entryList.get(i));
                }
            } else if (!type.isEmpty()) {
                if (line.contains(type)) {
                    trimmedList.add(entryList.get(i));
                }
            } else {
                trimmedList.add(entryList.get(i));
            }
        }

        findVocabulary(trimmedList, distanceIndex);
    }

    // Bestimmt die Entfernung zwischen EndMark und Vokabel
    private void runListDetermineDistance(String endMark, String vocabulary, String type) {
        int vocabularyIndex = 0;
        int endMarkIndex = 0;
        int distanceIndex = 0;
        boolean found = false;

        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).trim().equals(vocabulary)) {
                System.out.println("Vok" +
                        "abel bei " + i);
                vocabularyIndex = i;
                found = true;
            }
            if (texts.get(i).trim().equals(endMark) && found == true) {
                System.out.println("Gefunden bei Index: " + i);
                endMarkIndex = i;
                distanceIndex = endMarkIndex - vocabularyIndex;
                System.out.println("EndMark " + endMarkIndex + " VacIndex " + vocabularyIndex);
                System.out.println("DistanceIndex " + distanceIndex);
                break;
            } else {
                System.out.println("Not found");
            }
        }
        textBlockStorage(distanceIndex, endMark, String.valueOf(titleToAdd), type);
    }

    // Sucht nach Vokabeln in den formatierten Textblöcken
    private void findVocabulary(ArrayList<String> trimmedList, int distanceIndex) {
        buttonController = new ButtonController();
        ArrayList<String> returnVocList = new ArrayList<>();
        String pattern = "[^a-zA-Z0-9]";

        for (String block : trimmedList) {
            String[] lines = block.split("\n");
            try {
                String secondLastString = lines[lines.length - distanceIndex];
                String replacedRegex = secondLastString.replaceAll(pattern, "");
                returnVocList.add(replacedRegex);
            } catch (ArrayIndexOutOfBoundsException e) {
                uiManager.setMessageLabel("Bitte markiere Titel oder Art");
                break;
            }
        }
        ebookView.fillFlowPaneWithVocabulary(returnVocList);
    }

    // Test Methoden
    public String getEndMark() {
        return this.endMark;
    }

    public String getVocabulary() {
        return this.vocabulary;
    }

    public String getTitle() {
        return this.titleToAdd.toString();
    }

    public String getType() {
        return this.type;
    }


}
