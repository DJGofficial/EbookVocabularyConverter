package selfmade.ebookConverter.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import selfmade.ebookConverter.connection.GoogleTranslateAPIConnection;
import selfmade.ebookConverter.controller.*;
import selfmade.ebookConverter.model.ChoiceBoxItems;
import selfmade.ebookConverter.controller.EbookViewUIManager;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EbookView implements Initializable{

    @FXML
    AnchorPane anchorPane;

    @FXML
    Button fileButton, ankiButton, doneButton, createButton, translateButton, deleteButton, fillFlowPaneButton;
    @FXML
    TextField fileTextField;

    @FXML
    ChoiceBox<String> rootChoiceBox, optionChoiceBox, fieldsChoiceBox;

    @FXML
    ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;

    @FXML
    Label bottomMessageLabel = new Label();

    @FXML
    Label messageLabel = new Label();

    ChoiceBoxItems choiceBoxItems;
    FileController fileController= new FileController();
    ButtonController buttonController = new ButtonController();
    TrimAlgorithm trimAlgorithm;
    AnkiController ankiController;
    MessageController messageController = new MessageController();
    GoogleTranslateAPIConnection googleTranslateAPIConnection = new GoogleTranslateAPIConnection();
    EbookViewUIManager uiManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBoxItems choiceBoxItems = new ChoiceBoxItems();
        ankiController= new AnkiController();
        uiManager = ankiController.getUiManager();
        uiManager.initializeUIComponents(fileTextField, fillFlowPaneButton, flowPane, bottomMessageLabel, messageLabel);
        // uiManager = new EbookViewUIManager(fileTextField, fillFlowPaneButton, flowPane, bottomMessageLabel, messageLabel);
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

    @FXML
    private void setChooseFileButton() throws IOException {
        String chosenFilePath = fileController.chooseFile();
        uiManager.updateFileTextField(chosenFilePath);
    }

    @FXML
    private void setCreateButton() throws IOException {
        ArrayList<String> outputList = uiManager.getToggleButtonTextFromFlowPane();
        boolean retVal = fileController.userNameFile(outputList);
        uiManager.setBottomMessage(retVal, "Successfully created!");

    }

    @FXML
    public void deleteButtonClicked() {
        fileTextField.setText("");
        flowPane.getChildren().clear();
    }

    @FXML
    private void doneButtonClicked() {
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

    @FXML
    private void translateButtonClicked() throws IOException {
        googleTranslateAPIConnection.handleTranslation(this, flowPane);
    }


    @FXML
    private void fillFlowPaneButtonClicked() {
        flowPane.getChildren().addAll(fileController.createButtonsFromFile());
        doneButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    @FXML
    public void fillFlowPaneWithVocabulary(ArrayList<String> buttonList) {
        flowPane.getChildren().clear();
        buttonController.createVocButton(flowPane, buttonList);
    }

    @FXML
    public void fillFlowPaneTranslatedMap(HashMap<String, String> translatedMap) {
        ankiButton.setDisable(false);
        flowPane.getChildren().clear();
        flowPane.getChildren().addAll(buttonController.createTranslatedButton(flowPane, translatedMap));
    }

    @FXML
    public void ankiButtonClicked() {
        ankiController.handleAnkiDeck();
    }

    public EbookViewUIManager getUiManager() {
        return uiManager;
    }
}
