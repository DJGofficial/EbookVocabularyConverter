package selfmade.ebookConverter.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;


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

    @FXML
    Button fileButton, ankiButton, doneButton, createButton, translateButton, deleteButton;
    @FXML
    TextField fileTextField, createFileTextField;
    @FXML
    ChoiceBox<String> rootChoiceBox, optionChoiceBox, fieldsChoiceBox;
    @FXML
    ScrollPane scrollPane;
    @FXML
    FlowPane flowPane;
    @FXML
    Label bottomMessageLabel = new Label();
    @FXML
    Label messageLabel = new Label();


    ChoiceBoxItems choiceBoxItems;
    FileController fileController;
    ButtonController buttonController= new ButtonController();
    TrimAlgorithm trimAlgorithm;
    AnkiConnection ankiConnection;
    //? eventuell braucht man das this nicht
    MessageController messageController = new MessageController(this);

    //Methods
    @FXML
    private void setChooseFileButton() throws IOException {
        fileTextField.setText(fileController.chooseFile());
    }

    @FXML
    private void setCreateButton() {
        bottomMessageLabel.setText("");
        String name = createFileTextField.getText();
        if (!name.isEmpty()) {
            fileController.userNameFile(name);
            messageController.showSuccessMessage(bottomMessageLabel, "Successfully created!");
        } else {
            messageController.showErrorMessage(bottomMessageLabel, "Please enter a name for your file");
        }
    }

    @FXML
    public void deleteButtonClicked() {
        flowPane.getChildren().clear();
    }

    @FXML
    private void createButtonText() {
        flowPane.getChildren().addAll(fileController.createButtonsFromFile());
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
        GoogleTranslateAPIConnection connection;
        HashMap<String, String> translation = new HashMap<>();
        for (Node node : flowPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                translation.put(button.getText(), null);
            }
        }
        connection = new GoogleTranslateAPIConnection(translation, this);

    }

    //? Must move to ButtonController
    @FXML
    public void fillFlowPaneWithVocabulary(List<Button> buttonList) {
        flowPane.getChildren().clear();

        for (int i = 0; i < buttonList.size(); i++) {
            Button button = buttonList.get(i);
            flowPane.getChildren().add(button);

            button.setOnAction(event -> {
                Button clickedButton = (Button) event.getSource();
                int buttonIndex = buttonList.indexOf(clickedButton);

                if (buttonIndex >= 0) {
                    buttonList.remove(buttonIndex);
                    fillFlowPaneWithVocabulary(buttonList);
                }
            });
        }
    }

    @FXML
    public void fillFlowPaneTranslatedMap(HashMap<String, String> translatedMap) {
        ankiButton.setDisable(false);
        flowPane.getChildren().clear();
        flowPane.getChildren().addAll(buttonController.createTranslatedButton(flowPane,translatedMap));
    }

    @FXML
    public void ankiButtonClicked() throws Exception {
        AnkiDeckChoose ankiDeckChoose = new AnkiDeckChoose();
        choiceBoxItems = new ChoiceBoxItems();

        ankiConnection = new AnkiConnection(this);
        ObservableList<String> deckList = ankiConnection.fetchDeckNames();
        if (deckList != null && !deckList.isEmpty()) {
            ankiDeckChoose.callWindowAddDeckList(deckList);
            messageController.showSuccessMessage(bottomMessageLabel, "Decks successfull added");
        } else {
            messageController.showErrorMessage(bottomMessageLabel, "Could not transfer Cards");
        }

    }

    //Getter and Setter
    @FXML
    public void setMessageLabel(String message) {
        //Not sure yet if I need this
        messageController.showErrorMessage(messageLabel, message);
    }

    @FXML
    public void setBottomLabelMessage(String message, Boolean value) {
        if (value == false) {
            messageController.showErrorMessage(bottomMessageLabel, message);
        } else {
            messageController.showSuccessMessage(bottomMessageLabel, message);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fileController = new FileController();

        ChoiceBoxItems choiceBoxItems = new ChoiceBoxItems();

        rootChoiceBox.setItems(choiceBoxItems.getFirstItems());
        optionChoiceBox.setItems(choiceBoxItems.getSecondItems());
        fieldsChoiceBox.setItems(choiceBoxItems.getThirdItems());

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



