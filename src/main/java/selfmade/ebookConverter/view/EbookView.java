package selfmade.ebookConverter.view;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;

import selfmade.ebookConverter.controller.EbookController;
import selfmade.ebookConverter.model.EbookModel;
import selfmade.ebookConverter.model.TextColour;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EbookView extends HBox implements Initializable {

    @FXML
    Button fileButton = new Button();
    @FXML
    Button createButton = new Button();
    @FXML
    Button ankiButton = new Button();
    @FXML
    TextField fileTextField = new TextField();
    @FXML
    TextField createFileTextField = new TextField();
    @FXML
    Label createFileLabel = new Label();
    @FXML
    public ChoiceBox<String> choiceBox = new ChoiceBox();
 //   public String getChoiceBoxValue() {
   //     return choiceBox.getValue();
    //}
    @FXML
    ScrollPane scrollPane = new ScrollPane();
    @FXML
    FlowPane flowPane = new FlowPane();

  EbookController ebookController = new EbookController();

    ArrayList<TextColour> textColourList = TextColour.createTextColourList();

    //Methods
    @FXML
    void setChooseFileButton() throws IOException {
        fileTextField.setText(ebookController.chooseFile());
    }

    @FXML
    void setCreateButton() {
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
    void createButtonText() {
        flowPane.getChildren().addAll(ebookController.createButtonsFromFile());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("TextColourList "+textColourList.get(0).getName());
        choiceBox.setItems(FXCollections.observableArrayList(textColourList.get(0).getName(), textColourList.get(1).getName(), textColourList.get(2).getName(), textColourList.get(3).getName(), textColourList.get(4).getName()));
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            EbookModel.setButtonColour(newValue);
          //  System.out.println("NewValue "+newValue);
        });

    }

}