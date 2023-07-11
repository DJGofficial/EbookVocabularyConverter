package selfmade.ebookConverter.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import javafx.scene.layout.FlowPane;

import javafx.scene.paint.Color;

import selfmade.ebookConverter.controller.EbookController;
import selfmade.ebookConverter.model.ChoiceBoxItems;
import selfmade.ebookConverter.model.EbookModel;
import selfmade.ebookConverter.model.TextColour;


import java.io.IOException;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.util.ArrayList;
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
    FlowPane flowPane;
    @FXML
    Label createFileLabel;


    EbookController ebookController;
    EbookModel ebookModel= new EbookModel();

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
    private void doneButtonClicked(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ebookController = new EbookController();

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
        fieldsChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue)->{
            System.out.println("ChoiceboxStatus is "+fieldsChoiceBox.getValue());
           ebookModel.setButtonChoiceBoxStatus(fieldsChoiceBox.getValue());
        });
    }
}



