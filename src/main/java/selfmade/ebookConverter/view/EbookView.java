package selfmade.ebookConverter.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;


import javafx.scene.layout.FlowPane;

import javafx.scene.paint.Color;

import selfmade.ebookConverter.controller.FileController;
import selfmade.ebookConverter.controller.TrimAlgorithm;
import selfmade.ebookConverter.model.ChoiceBoxItems;
import selfmade.ebookConverter.controller.ButtonController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EbookView implements Initializable {

    @FXML
    Button fileButton, ankiButton, doneButton, createButton;
    @FXML
    TextField fileTextField, createFileTextField;
    @FXML
    ChoiceBox<String> rootChoiceBox, optionChoiceBox, fieldsChoiceBox;
    @FXML
    ChoiceBoxItems choiceBoxItems;
    @FXML
    ScrollPane scrollPane;
    @FXML
    public FlowPane flowPane;
    @FXML
    Label createFileLabel;
    @FXML
    Label messageLabel = new Label();


    FileController fileController;
    ButtonController buttonController;
    TrimAlgorithm trimAlgorithm;

    //Methods
    @FXML
    private void setChooseFileButton() throws IOException {
        fileTextField.setText(fileController.chooseFile());
    }

    @FXML
    private void setCreateButton() {
        createFileLabel.setText("");
        String name = createFileTextField.getText();
        if (!name.isEmpty()) {
            fileController.userNameFile(name);
            createFileLabel.setTextFill(Color.GREEN);
            createFileLabel.setText("Successfull created!");
        } else {
            createFileLabel.setTextFill(Color.RED);
            createFileLabel.setText("Please enter a name for your file");
        }
    }

    @FXML
    private void createButtonText() {
        flowPane.getChildren().addAll(fileController.createButtonsFromFile());
    }

    @FXML
    private void doneButtonClicked() {
        ObservableList<Node> content = flowPane.getChildren();
        trimAlgorithm = new TrimAlgorithm(content);
        messageLabel.setText("");

        boolean[] result = trimAlgorithm.checkContent();
        boolean hasEndMark = result[0];
        boolean hasVocabulary = result[1];

        if (hasEndMark && hasVocabulary) {
            trimAlgorithm.getContentToTextFragments();
        } else {
            if (!hasEndMark) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("End-Mark fehlt!");
            }
            if (!hasVocabulary) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Vokabel fehlt!");
            }
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



