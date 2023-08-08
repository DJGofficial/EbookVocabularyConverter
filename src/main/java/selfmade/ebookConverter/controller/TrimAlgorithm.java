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
    private ArrayList<String> entryList = new ArrayList<>();


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
        divideBlocksAddToList(endMark, vocabulary);
    }

    private void extractVocabulary(String endMark, String vocabulary, StringBuilder titleToAdd, String type) {
        System.out.print(endMark + " " + vocabulary + " " + titleToAdd + " " + type);
        for (String text : texts) {
            if (endMark.equals(text)) {
                System.out.println("Does");
            }
        }
    }

    private void divideBlocksAddToList(String endMark, String vocabulary) {
        System.out.println("EndMark " + endMark + "!");
        boolean found = false;

        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).trim().equals(endMark)) {
                System.out.println("Gefunden bei Index: " + i);
                found = true;
                break; // Wenn das Element gefunden wurde, beende die Schleife
            }else{
                System.out.println("Not found");
            }


        }
        StringBuilder entryBuilder = new StringBuilder();
  /*
        for (String inputText : texts) {
            int currentIndex = 0;

          while (true) {
                int targetIndex = inputText.indexOf(endMark, currentIndex);
                System.out.println("currentIndex: " + currentIndex + ", targetIndex: " + targetIndex);

                if (targetIndex == -1) {
                    break;
                }
                String extractedText = inputText.substring(currentIndex, targetIndex);
                System.out.println("Extracted Text: " + extractedText);

                entryList.add(extractedText);
                currentIndex = targetIndex + endMark.length();
            }


        for (String inputText : texts) {
            int targetIndex = inputText.indexOf(endMark);
            if (targetIndex != -1) {
                String extractedText = inputText.substring(0, targetIndex);
                entryList.add(extractedText);
            }

        }

        // Ausgabe der extrahierten Textteile
        for (int i = 0; i < entryList.size(); i++) {
            System.out.println("Index " + i + ": " + entryList.get(i));
        }

        System.out.println(entryList.get(3));
         */
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
