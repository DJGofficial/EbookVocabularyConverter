package selfmade.ebookConverter.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static selfmade.ebookConverter.model.TextColour.textColourList;

public class ChoiceBoxItems {
    private ObservableList<String> firstItems;
    private ObservableList<String> secondItems;
    private static ObservableList<String> thirdItems;

    public ChoiceBoxItems() {
        firstItems = FXCollections.observableArrayList("Anki", "Remnote", "PlainText");
        secondItems = FXCollections.observableArrayList();
        thirdItems = FXCollections.observableArrayList();
    }

    public ObservableList<String> getFirstItems() {
        return firstItems;
    }

    public  ObservableList<String> getSecondItems() {
        return secondItems;
    }

    public  ObservableList<String> getThirdItems() {
        System.out.println("ThirdItems "+thirdItems);
        return thirdItems;
    }

    public ObservableList<String> updateSecondItems(String selectedFirstItem) {
        if (selectedFirstItem.equals("Anki")) {
            return FXCollections.observableArrayList("Vokabel hinzufügen");
        } else if (selectedFirstItem.equals("Remnote")) {
            return FXCollections.observableArrayList("Option A", "Option B");
        } else if (selectedFirstItem.equals("PlainText")) {
            return FXCollections.observableArrayList("Option C", "Option D");
        }
        return FXCollections.emptyObservableList();
    }

    public ObservableList<String> updateThirdItems(String selectedSecondItem) {
        if (selectedSecondItem.equals("Vokabel hinzufügen")) {
            return FXCollections.observableArrayList( textColourList.get(0).getName(),
                    textColourList.get(1).getName(),
                    textColourList.get(2).getName());
        } else if (selectedSecondItem.equals("Option A")) {
            return FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
        }
        return FXCollections.emptyObservableList();
    }
}

