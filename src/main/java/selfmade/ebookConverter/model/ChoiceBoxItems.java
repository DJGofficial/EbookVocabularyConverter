package selfmade.ebookConverter.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static selfmade.ebookConverter.model.TextColour.textColourList;

public class ChoiceBoxItems {
    private ObservableList<String> firstItems;
    private ObservableList<String> secondItems;
    private static ObservableList<String> thirdItems;

    public ChoiceBoxItems() {
        firstItems = FXCollections.observableArrayList("Anki");
        secondItems = FXCollections.observableArrayList();
        thirdItems = FXCollections.observableArrayList();
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
             */
        }
        return FXCollections.emptyObservableList();
    }

    public ObservableList<String> updateThirdItems(String selectedSecondItem) {
       TextColour textColour= new TextColour();
        if (selectedSecondItem.equals("Vokabel hinzufügen")) {
            return FXCollections.observableArrayList(textColourList.get(0).getName(),
                    textColourList.get(1).getName(),
                    textColourList.get(2).getName(),
                    textColourList.get(3).getName());
        /*
        } else if (selectedSecondItem.equals("Option A")) {
            return FXCollections.observableArrayList("Option 1", "Option 2");
         */
        }
        return FXCollections.emptyObservableList();
    }
}

