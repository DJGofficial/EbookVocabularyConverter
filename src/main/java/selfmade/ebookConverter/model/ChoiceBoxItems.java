package selfmade.ebookConverter.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static selfmade.ebookConverter.model.TextColour.textColourList;

public class ChoiceBoxItems {
    private ObservableList<String> firstItems;
    private ObservableList<String> secondItems;
    private static ObservableList<String> thirdItems;

    private ObservableList<String> deckList;


    public ChoiceBoxItems() {
        firstItems = FXCollections.observableArrayList("Anki");
        secondItems = FXCollections.observableArrayList();
        thirdItems = FXCollections.observableArrayList();
    }

    public ObservableList<String> getDeckList() {
        return deckList;
    }

    public void setDeckList(ObservableList<String> deckList) {
        this.deckList = deckList;
    }

    public ObservableList<String> getFirstItems() {
        return firstItems;
    }

    public ObservableList<String> getSecondItems() {
        return secondItems;
    }

    public ObservableList<String> getThirdItems() {
        return thirdItems;
    }

    public ObservableList<String> updateSecondItems(String selectedFirstItem) {
        if (selectedFirstItem.equals("Anki")) {
            return FXCollections.observableArrayList("Vokabel hinzufügen");
            /*
        } else if (selectedFirstItem.equals("Remnote")) {
            return FXCollections.observableArrayList("Option A", "Option B");
        } else if (selectedFirstItem.equals("PlainText")) {
            return FXCollections.observableArrayList("Option C", "Option D");

             */
        }
        return FXCollections.emptyObservableList();
    }

    public ObservableList<String> updateThirdItems(String selectedSecondItem) {
        if (selectedSecondItem.equals("Vokabel hinzufügen")) {
            return FXCollections.observableArrayList(textColourList.get(0).getName(),
                    textColourList.get(1).getName(),
                    textColourList.get(2).getName(),
                    textColourList.get(3).getName());
        /*
        } else if (selectedSecondItem.equals("Option A")) {
            return FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");

         */
        }
        return FXCollections.emptyObservableList();
    }
}

