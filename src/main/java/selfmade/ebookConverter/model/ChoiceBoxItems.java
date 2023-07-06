package selfmade.ebookConverter.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static selfmade.ebookConverter.model.TextColour.textColourList;

public class ChoiceBoxItems {
    private ObservableList<String> firstItems;
    private ObservableList<String> secondItems;
    private static ObservableList<String> thirdItems;
  // static String status=getThirdItems().getValue();

    public ChoiceBoxItems() {
        firstItems = FXCollections.observableArrayList("Anki", "Remnote", "PlainText");
        secondItems = FXCollections.observableArrayList();
        thirdItems = FXCollections.observableArrayList();
    }
/*
    public static String getStatus() {
        return status;
    }
 */
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
        // Logik zum Aktualisieren der zweiten Choice Box-Optionen basierend auf der Auswahl in der ersten Choice Box
        if (selectedFirstItem.equals("Anki")) {
            return FXCollections.observableArrayList("Add Note");
        } else if (selectedFirstItem.equals("Option 2")) {
            return FXCollections.observableArrayList("Option D", "Option E", "Option F");
        } else if (selectedFirstItem.equals("Option 3")) {
            return FXCollections.observableArrayList("Option G", "Option H", "Option I");
        }
        return FXCollections.emptyObservableList();
    }

    public ObservableList<String> updateThirdItems(String selectedSecondItem) {
        // Logik zum Aktualisieren der dritten Choice Box-Optionen basierend auf der Auswahl in der zweiten Choice Box
        if (selectedSecondItem.equals("Add Note")) {
            return FXCollections.observableArrayList( textColourList.get(0).getName(),
                    textColourList.get(1).getName(),
                    textColourList.get(2).getName(),
                    textColourList.get(3).getName(),
                    textColourList.get(4).getName());
        } else if (selectedSecondItem.equals("Option B")) {
            return FXCollections.observableArrayList("Option P", "Option Q", "Option R");
        }
        return FXCollections.emptyObservableList();
    }
}

