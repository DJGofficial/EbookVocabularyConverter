package selfmade.ebookConverter.view;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;

import org.controlsfx.control.action.Action;
import selfmade.ebookConverter.controller.EbookController;
import selfmade.ebookConverter.controller.SceneController;
import selfmade.ebookConverter.model.EbookModel;
import selfmade.ebookConverter.model.TextColour;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EbookView implements Initializable {

    @FXML
    Button fileButton;
    @FXML
    Button ankiButton;
    @FXML
    TextField fileTextField;

    @FXML
    public ChoiceBox<String> fieldsChoiceBox;
    @FXML
    ScrollPane scrollPane;
    @FXML
    FlowPane flowPane;
    @FXML
    Button doneButton;
    @FXML
    Button createButton = new Button();
    @FXML
    TextField createFileTextField = new TextField();
    @FXML
    Label createFileLabel = new Label();
    EbookController ebookController;
    ArrayList<TextColour> textColourList;

    //Methods
    @FXML
    private void setChooseFileButton() throws IOException {
        fileTextField.setText(ebookController.chooseFile());
    }

    @FXML
    private void setCreateButton() {
        createFileLabel.setText("");
        String name = createFileTextField.getText();
        if (!name.isEmpty()) {
            ebookController.userNameFile(name);
            createFileLabel.setText("Successfull created!");

        } else {
            createFileLabel.setTextFill(Color.RED);
            createFileLabel.setText("Please enter a name for your file");
        }
    }

    @FXML
    private void createButtonText() {
        flowPane.getChildren().addAll(ebookController.createButtonsFromFile());
    }

    @FXML
    private void doneButtonClicked() throws IOException {
        ActionEvent event= new ActionEvent();
        SceneController.switchScene(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ebookController = new EbookController();
        textColourList = TextColour.createTextColourList();

        System.out.println("TextColourList " + textColourList.get(0).getName());

        fieldsChoiceBox.setItems(FXCollections.observableArrayList(
                textColourList.get(0).getName(),
                textColourList.get(1).getName(),
                textColourList.get(2).getName(),
                textColourList.get(3).getName(),
                textColourList.get(4).getName()));
        fieldsChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            EbookModel.setButtonColour(newValue);
        });

    }

}