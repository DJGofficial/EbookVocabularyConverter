package selfmade.ebookConverter.controller;

import selfmade.ebookConverter.view.EbookView;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;

import java.util.*;
import java.util.List;

public class TrimAlgorithm {

    private String title = "";
    private StringBuilder titleToAdd = new StringBuilder();
    private String type = titleToAdd.toString();
    private String endMark = "";
    private String vocabulary = "";
    private ObservableList<Node> contentList;

    private List<String> texts = new ArrayList<>();
    private ArrayList<String> entryList = new ArrayList<>();

    EbookView ebookView;

    public TrimAlgorithm(ObservableList<Node> content, EbookView ebookView) {
        this.ebookView = ebookView;
        this.contentList = content;

    }

    //Checks if EndMark and Vocabulary are marked as minimal requirement for the algorithm to work
    public boolean[] checkContent() {
        boolean hasEndMark = false;
        boolean hasVocabulary = false;
        for (Node node : contentList) {
            ToggleButton toggleButton = (ToggleButton) node;
            String buttonText = toggleButton.getText();
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

    //Format Button to designation and save it to List
    public void getContentToTextFragments() {
        for (Node node : contentList) {
            ToggleButton toggleButton = (ToggleButton) node;
            String buttonText = toggleButton.getText();
            texts.add(buttonText + " ");
        }
        getMarkings();
    }

    //Search content for begin/end marks
    private void getMarkings() {
        for (Node node : contentList) {
            ToggleButton toggleButton = (ToggleButton) node;
            String buttonText = toggleButton.getText();
            String styleString = node.lookup(".toggle-button").getStyle();
            switch (styleString) {
                case "-fx-background-color: #D9FFD9":
                    endMark = buttonText.trim();//text before endMark is added as unit
                    break;
                case "-fx-background-color: #FFD9D9":
                    vocabulary = buttonText;  //word that is later translated
                    break;
                case "-fx-background-color: #FFFFD9":
                    titleToAdd.append(buttonText + " ");//Only include books with this title
                    //ATTENTION SPACE COULD BE ADDED AFTER LAST POSITION
                    break;
                case "-fx-background-color: #D9E5FF":
                    type = buttonText;//Only include text with this type
                    break;
            }
        }
        extractVocabulary(endMark, vocabulary, titleToAdd, type);
        textBlockStorage(endMark);
        runListDetermineDistance(endMark, vocabulary);
    }

    private void extractVocabulary(String endMark, String vocabulary, StringBuilder titleToAdd, String type) {
        System.out.print(endMark + " " + vocabulary + " " + titleToAdd + " " + type);

    }

    private void runListDetermineDistance(String endMark, String vocabulary) {
        int vocabularyIndex = 0;
        int endMarkIndex = 0;
        int distanceIndex = 0;

        boolean found = false;
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).trim().equals(vocabulary)) {
                System.out.println("Vokabel bei " + i);
                vocabularyIndex = i;
            }
            if (texts.get(i).trim().equals(endMark)) {
                System.out.println("Gefunden bei Index: " + i);
                endMarkIndex = i;
                distanceIndex = endMarkIndex - vocabularyIndex;
                System.out.println("DistanceIndex " + distanceIndex);
                found = true;
                break;
            } else {
                System.out.println("Not found");
            }

        }
        findVocabulary(distanceIndex);


    }

    private void findVocabulary(int distanceIndex) {
        for (String block : entryList) {
            String[] lines = block.split("\n");
            try {
                String vorletzterString = lines[lines.length - distanceIndex];
            } catch (ArrayIndexOutOfBoundsException e) {
                ebookView.messageChange();
                break;
            }
        }
        ebookView.fillFlowPaneWithVocabulary();
    }

    private void textBlockStorage(String endMark) {
        StringBuilder currentBlock = new StringBuilder();

        for (String line : texts) {
            if (line.trim().equals(endMark)) {
                entryList.add(currentBlock.toString());
                currentBlock = new StringBuilder();
            } else {
                currentBlock.append(line).append("\n");
            }
        }
        System.out.println("Size " + entryList.size());
        //  for (String block : entryList) {
        //  }
    }


    private void findAndPrintSpecialEntries() {
        /*for (String text : texts) {
            if (text.length() >= 2 && !Character.isLetterOrDigit(text.charAt(0)) && !Character.isLetterOrDigit(text.charAt(text.length() - 1))) {
                System.out.println(text);
            }
        }
         */

    }

}
