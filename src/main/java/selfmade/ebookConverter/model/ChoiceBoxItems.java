package selfmade.ebookConverter.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static selfmade.ebookConverter.model.TextColour.textColourList;

/**
 * Verwaltet die Elemente von verschiedenen ChoiceBoxen.
 */
public class ChoiceBoxItems {
    private ObservableList<String> firstItems;
    private ObservableList<String> secondItems;
    private static ObservableList<String> thirdItems;

    /**
     * Initialisiert die ChoiceBoxItems.
     */
    public ChoiceBoxItems() {
        firstItems = FXCollections.observableArrayList("Anki");
        secondItems = FXCollections.observableArrayList();
        thirdItems = FXCollections.observableArrayList();
    }

    /**
     * @return Gibt die erste Gruppe von Elementen zurück.
     */
    public ObservableList<String> getFirstItems() {
        return firstItems;
    }

    /**
     * @return Gibt die zweite Gruppe von Elementen zurück.
     */
    public ObservableList<String> getSecondItems() {
        return secondItems;
    }

    /**
     * @return Gibt die dritte Gruppe von Elementen zurück.
     */
    public ObservableList<String> getThirdItems() {
        return thirdItems;
    }

    /**
     * Aktualisiert die zweite Gruppe von Elementen basierend auf der Auswahl des ersten Elements.
     *
     * @param selectedFirstItem Das ausgewählte Element der ersten ChoiceBox.
     * @return Eine ObservableList der aktualisierten Elemente.
     */
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

    /**
     * Aktualisiert die dritte Gruppe von Elementen basierend auf der Auswahl des zweiten Elements.
     *
     * @param selectedSecondItem Das ausgewählte Element der zweiten ChoiceBox.
     * @return Eine ObservableList der aktualisierten Elemente.
     */
    public ObservableList<String> updateThirdItems(String selectedSecondItem) {
        TextColour textColour = new TextColour();
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

