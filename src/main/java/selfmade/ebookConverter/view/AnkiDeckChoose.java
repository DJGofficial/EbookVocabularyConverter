package selfmade.ebookConverter.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import selfmade.ebookConverter.MainStage;
import selfmade.ebookConverter.controller.AnkiController;
import selfmade.ebookConverter.model.TextAttributesObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Die AnkiDeckChoose Klasse dient zur Verwaltung der UI-Elemente
 * für die Auswahl von Anki-Decks.
 * <p>
 * Diese Klasse implementiert das Initializable Interface von JavaFX,
 * um die Initialisierung von UI-Elementen zu erleichtern.
 */
public class AnkiDeckChoose implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    ChoiceBox<String> choiceBoxAnkiDecks = new ChoiceBox<>();
    private static ObservableList<String> deckListLocal = FXCollections.observableArrayList();
    @FXML
    MainStage mainStage = new MainStage();
    AnkiController ankiController;
    TextAttributesObject textAttributesObject = new TextAttributesObject<>();

    /**
     * Aktualisiert die Deckliste und zeigt ein neues Fenster.
     *
     * @param deckList Die neue Liste der Decks.
     */
    @FXML
    public void callWindowAddDeckList(ObservableList<String> deckList) {
        this.deckListLocal.addAll(deckList);
        mainStage.showNewStage();
    }

    /**
     * Diese Methode wird aufgerufen, wenn die Deck-Auswahl bestätigt wird.
     * Sie erstellt und fügt Karten zum ausgewählten Deck hinzu.
     * <p>
     * Diese Methode kann eine IOException werfen.
     *
     * @throws IOException Wenn ein I/O-Fehler auftritt.
     */
    @FXML
    private void deckConfirmButtonClicked() throws IOException {
        List<String> texter = new ArrayList<>();
        texter = ankiController.createAndAddCards(choiceBoxAnkiDecks.getValue(), textAttributesObject.getTranslatedMap());
        for (String entry : texter) {
            System.out.println(entry);
            textArea.setText(entry + "\n");
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (String entry : texter) {
            stringBuilder.append(entry).append("\n");
        }
        textArea.setText(stringBuilder.toString());
        System.out.println("Size " + texter.size());
    }

    /**
     * Initialisiert die UI-Elemente und Controller.
     * Diese Methode ist durch das Initializable Interface vorgeschrieben.
     *
     * @param url            Der Ort der zugehörigen FXML-Datei.
     * @param resourceBundle Die Ressourcen für die Lokalisierung.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAnkiDecks.getItems().addAll(this.deckListLocal);
        ankiController = new AnkiController();

    }
}




