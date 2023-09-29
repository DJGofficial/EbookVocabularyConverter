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
import selfmade.ebookConverter.controller.FileController;
import selfmade.ebookConverter.controller.MessageController;
import selfmade.ebookConverter.controller.TrimAlgorithm;
import selfmade.ebookConverter.model.ChoiceBoxItems;
import selfmade.ebookConverter.controller.ButtonController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EbookView implements Initializable {

    // Definition der FXML-Elemente
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button fileButton, ankiButton, doneButton, createButton, translateButton, deleteButton, fillFlowPaneButton;
    @FXML
    TextField fileTextField;
    public TextField getTestFileTextField() {
        return this.fileTextField;
    }
    @FXML
    ChoiceBox<String> rootChoiceBox, optionChoiceBox, fieldsChoiceBox;
    @FXML
    ScrollPane scrollPane;
    @FXML
    FlowPane flowPane;
    public FlowPane getTestFlowPane() {
        return this.flowPane;
    }
    @FXML
    Label bottomMessageLabel = new Label();
    @FXML
    Label messageLabel = new Label();

    // Initialisierung von Controller und Verbindungen
    ChoiceBoxItems choiceBoxItems;
    FileController fileController;
    ButtonController buttonController = new ButtonController();
    TrimAlgorithm trimAlgorithm;
    AnkiConnection ankiConnection;
    MessageController messageController = new MessageController();
    GoogleTranslateAPIConnection googleTranslateAPIConnection = new GoogleTranslateAPIConnection();

    // Hier beginnen die Methoden für die Controller-Funktionalitäten

    // Methode für das Auswählen einer Datei
    @FXML
    private void setChooseFileButton() throws IOException {
        fileTextField.setText(fileController.chooseFile());
        fillFlowPaneButton.setDisable(false);
    }

    // Methode für das Erstellen von Benutzerdateien
    @FXML
    private void setCreateButton() throws IOException {
        bottomMessageLabel.setText("");
        ArrayList<String> outputList = new ArrayList<>();
        for (Node node : flowPane.getChildren()) {
            if (node instanceof ToggleButton) {
                ToggleButton button = (ToggleButton) node;
                String buttonText = button.getText();
                outputList.add(buttonText);
            }
        }
        boolean retVal = fileController.userNameFile(outputList);
        if (retVal == true) {
            messageController.showSuccessMessage(bottomMessageLabel, "Successfully created!");
        }
    }

    // Methode zum Löschen von Inhalten
    @FXML
    public void deleteButtonClicked() {
        fileTextField.setText("");
        flowPane.getChildren().clear();
    }

    // Methode für den Abschluss
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

    // Methode für die Übersetzung
    @FXML
    private void translateButtonClicked() throws IOException {
        // Übersetzen der Inhalte mit der Google Translate API
        HashMap<String, String> translation = new HashMap<>();
        for (Node node : flowPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                translation.put(button.getText(), null);
            }
        }
        googleTranslateAPIConnection.translateAndReturnHashMap(this, translation);
        ankiButton.setDisable(false);
    }

    // Methode zum Hinzufügen von Inhalten aus einer Datei
    @FXML
    private void fillFlowPaneButtonClicked() {
        flowPane.getChildren().addAll(fileController.createButtonsFromFile());
        doneButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    // Methode zum Hinzufügen von Vokabeln in den FlowPane
    @FXML
    public void fillFlowPaneWithVocabulary(ArrayList<String> buttonList) {
        flowPane.getChildren().clear();
        buttonController.createVocButton(flowPane, buttonList);
    }

    // Methode zum Hinzufügen von übersetzten Buttons in den FlowPane
    @FXML
    public void fillFlowPaneTranslatedMap(HashMap<String, String> translatedMap) {
        ankiButton.setDisable(false);
        flowPane.getChildren().clear();
        flowPane.getChildren().addAll(buttonController.createTranslatedButton(flowPane, translatedMap));
    }

    // Methode für das Klicken des Anki-Buttons
    @FXML
    public void ankiButtonClicked() {
        bottomMessageLabel.setText("");
        AnkiDeckChoose ankiDeckChoose = new AnkiDeckChoose();
        choiceBoxItems = new ChoiceBoxItems();

        // Verbindung mit Anki herstellen und Deck-Namen abrufen
        ankiConnection = new AnkiConnection(this);
        ObservableList<String> deckList = ankiConnection.fetchDeckNames();

        if (deckList != null && !deckList.isEmpty()) {
            ankiDeckChoose.callWindowAddDeckList(deckList);
        } else {
            messageController.showErrorMessage(bottomMessageLabel, "Bitte stelle Verbindung mit Anki über AnkiConnect her");
        }
    }

    // Getter und Setter für Nachrichten-Labels
    @FXML
    public void setMessageLabel(String message) {
        messageController.showErrorMessage(messageLabel, message);
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    @FXML
    public void setBottomLabelMessage(String message, Boolean value) {
        if (value == false) {
            messageController.showErrorMessage(bottomMessageLabel, message);
        } else {
            messageController.showSuccessMessage(bottomMessageLabel, message);
        }
    }

    // Initialisierungsmethode für den Controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileController = new FileController();
        ChoiceBoxItems choiceBoxItems = new ChoiceBoxItems();

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
}
