package selfmade.ebookConverter.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import selfmade.ebookConverter.connection.AnkiConnection;
import selfmade.ebookConverter.connection.GoogleTranslateAPIConnection;
import selfmade.ebookConverter.controller.*;
import selfmade.ebookConverter.model.ChoiceBoxItems;
import selfmade.ebookConverter.model.EbookViewUIManager;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Verantwortlich für die Darstellung der E-Book-Ansicht und die Steuerung der Interaktionen.
 */
public class EbookView implements Initializable {

    /**
     * AnchorPane zur Organisation der UI-Komponenten.
     */
    @FXML
    AnchorPane anchorPane;
    /**
     * Verschiedene Buttons für Benutzeraktionen.
     */
    @FXML
    Button fileButton, ankiButton, doneButton, createButton, translateButton, deleteButton, fillFlowPaneButton;
    /**
     * Textfeld zur Darstellung des Dateipfads.
     */
    @FXML
    TextField fileTextField;
    /**
     * ChoiceBox-Elemente zur Auswahl von Optionen.
     */
    @FXML
    ChoiceBox<String> rootChoiceBox, optionChoiceBox, fieldsChoiceBox;
    /**
     * ScrollPane zur Anzeige dynamischer Inhalte.
     */
    @FXML
    ScrollPane scrollPane;
    /**
     * FlowPane zur dynamischen Anordnung der Buttons.
     */
    @FXML
    FlowPane flowPane;
    /**
     * Label für die Anzeige von Nachrichten.
     */
    @FXML
    Label bottomMessageLabel = new Label();

    @FXML
    Label messageLabel = new Label();

    // Initialisierung von Controller und Verbindungen
    ChoiceBoxItems choiceBoxItems;
    FileController fileController;
    ButtonController buttonController = new ButtonController();
    TrimAlgorithm trimAlgorithm;
    AnkiController ankiController;
    MessageController messageController = new MessageController();
    GoogleTranslateAPIConnection googleTranslateAPIConnection = new GoogleTranslateAPIConnection();
    EbookViewUIManager uiManager;
    /**
     * Initialisiert die GUI und setzt die Startwerte.
     *
     * @param url            Eine URL zu einer Ressource. Nicht verwendet.
     * @param resourceBundle Ein ResourceBundle. Nicht verwendet.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileController = new FileController();
        ChoiceBoxItems choiceBoxItems = new ChoiceBoxItems();
        uiManager = new EbookViewUIManager(fileTextField, fillFlowPaneButton, flowPane, bottomMessageLabel, messageLabel);
        // Initialisierung der ChoiceBox-Elemente
        rootChoiceBox.setItems(choiceBoxItems.getFirstItems());
        optionChoiceBox.setItems(choiceBoxItems.getSecondItems());
        fieldsChoiceBox.setItems(choiceBoxItems.getThirdItems());

        // Listener für die ChoiceBoxen hinzufügen
        rootChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            ObservableList<String> updatedSecondItems = choiceBoxItems.updateSecondItems(newValue);
            optionChoiceBox.setItems(updatedSecondItems);
            fieldsChoiceBox.setItems(FXCollections.emptyObservableList());
        });

        optionChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            ObservableList<String> updatedThirdItems = choiceBoxItems.updateThirdItems(newValue);
            fieldsChoiceBox.setItems(updatedThirdItems);
        });

        fieldsChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            buttonController.setButtonChoiceBoxStatus(fieldsChoiceBox.getValue());
        });
    }

    /**
     * Lässt den Benutzer eine Datei auswählen und aktualisiert das Textfeld entsprechend.
     *
     * @throws IOException Wenn das Auslesen der Datei fehlschlägt.
     */
    @FXML
    private void setChooseFileButton() throws IOException {
      //  fileTextField.setText(fileController.chooseFile());
      //  fillFlowPaneButton.setDisable(false);
        String chosenFilePath = fileController.chooseFile();
        uiManager.updateFileTextField(chosenFilePath);
    }

    /**
     * Erstellt Benutzerdateien basierend auf der aktuellen Auswahl.
     *
     * @throws IOException Wenn das Schreiben der Datei fehlschlägt.
     */
    @FXML
    private void setCreateButton() throws IOException {
        ArrayList<String> outputList = uiManager.getToggleButtonTextFromFlowPane();
        boolean retVal = fileController.userNameFile(outputList);
        uiManager.setBottomMessage(retVal, "Successfully created!");

    }

    /**
     * Löscht alle Elemente aus dem FlowPane.
     */
    @FXML
    public void deleteButtonClicked() {
        fileTextField.setText("");
        flowPane.getChildren().clear();
    }

    /**
     * Prüft den aktuellen Inhalt und bereitet die Übersetzung vor.
     */
    @FXML
    private void doneButtonClicked() {
        // Überprüfung des Inhalts auf Endmarken und Vokabeln
        ObservableList<Node> content = flowPane.getChildren();
        trimAlgorithm = new TrimAlgorithm(content, this);
        messageLabel.setText("");

        boolean[] result = trimAlgorithm.checkContent();
        boolean hasEndMark = result[0];
        boolean hasVocabulary = result[1];

        if (hasEndMark && hasVocabulary) {
            translateButton.setDisable(false);
            trimAlgorithm.getContentToTextFragments();
        } else {
            if (!hasEndMark) {
                messageController.showErrorMessage(messageLabel, "End-Mark fehlt!");
            }
            if (!hasVocabulary) {
                messageController.showErrorMessage(messageLabel, "Vokabel fehlt!");
            }
        }
    }

    /**
     * Führt die Übersetzung der ausgewählten Textelemente durch.
     *
     * @throws IOException Wenn der Übersetzungsdienst nicht erreicht werden kann.
     */
    @FXML
    private void translateButtonClicked() throws IOException {
        googleTranslateAPIConnection.handleTranslation(this, flowPane);        // Übersetzen der Inhalte mit der Google Translate API
    }

    /**
     * Füllt das FlowPane mit den Inhalten aus der ausgewählten Datei.
     */
    @FXML
    private void fillFlowPaneButtonClicked() {
        flowPane.getChildren().addAll(fileController.createButtonsFromFile());
        doneButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    /**
     * Aktualisiert das FlowPane mit einer Liste von Vokabeln.
     *
     * @param buttonList Die Liste der Vokabeln.
     */
    @FXML
    public void fillFlowPaneWithVocabulary(ArrayList<String> buttonList) {
        flowPane.getChildren().clear();
        buttonController.createVocButton(flowPane, buttonList);
    }

    /**
     * Aktualisiert das FlowPane mit übersetzten Inhalten.
     *
     * @param translatedMap Eine HashMap der übersetzten Begriffe.
     */
    @FXML
    public void fillFlowPaneTranslatedMap(HashMap<String, String> translatedMap) {
        ankiButton.setDisable(false);
        flowPane.getChildren().clear();
        flowPane.getChildren().addAll(buttonController.createTranslatedButton(flowPane, translatedMap));
    }

    /**
     * Führt Aktionen aus, wenn der Anki-Button geklickt wird.
     */
    @FXML
    public void ankiButtonClicked() {
        ankiController= new AnkiController();
       // bottomMessageLabel.setText("");
        // Verbindung mit Anki herstellen und Deck-Namen abrufen
        ankiController.handleAnkiDeck(this, uiManager);
      /* ankiConnection = new AnkiConnection(this,uiManager);
        ObservableList<String> deckList = ankiConnection.fetchDeckNames();

        if (deckList != null && !deckList.isEmpty()) {
           ankiDeckChoose.callWindowAddDeckList(deckList);
        } else {
            messageController.showErrorMessage(bottomMessageLabel, "Bitte stelle Verbindung mit Anki über AnkiConnect her");
        }

       */
    }



}
