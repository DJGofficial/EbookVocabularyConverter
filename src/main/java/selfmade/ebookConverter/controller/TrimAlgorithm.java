package selfmade.ebookConverter.controller;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import selfmade.ebookConverter.model.TextColour;

import java.util.ArrayList;
import java.util.List;

public class TrimAlgorithm {

    private String title = "";
    private StringBuilder titleToAdd = new StringBuilder();
    private String type = titleToAdd.toString();
    private String endMark = "";
    private String vocabulary = "";
    private ObservableList<Node> contentList;

    private List<String> texts = new ArrayList<>();
    private List<String> entryList = new ArrayList<>();


    public TrimAlgorithm(ObservableList<Node> content) {

        this.contentList = content;

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
                    endMark = buttonText;//text before endMark is added as unit
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
        divideBlocksAddToList(endMark);
    }

    private void extractVocabulary(String endMark, String vocabulary, StringBuilder titleToAdd, String type) {
        System.out.print(endMark + " " + vocabulary + " " + titleToAdd + " " + type);
        for (String text : texts) {
            //    System.out.print(text);
        }
    }

    private void divideBlocksAddToList(String endMark) {
        StringBuilder entryBuilder = new StringBuilder();

        for (String text : texts) {
            if (text.equals(endMark)) {
                entryList.add(entryBuilder.toString());
                entryBuilder.setLength(0);
            } else {
                entryBuilder.append(text).append(" ");
            }
        }
        if (entryBuilder.length() > 0) {
            entryList.add(entryBuilder.toString());
        }

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
