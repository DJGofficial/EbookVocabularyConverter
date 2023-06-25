package selfmade.ebookConverter.view;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import selfmade.ebookConverter.model.EbookModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    String choiceBoxOptions[] = {"Notes", "Bookmarks", "Notes and bookmarks"};
    @FXML
    ChoiceBox choiceBox = new ChoiceBox();


    //Methods
    @FXML
    void selectFile() throws IOException {
        fileTextField.setText(EbookModel.chooseFile());

    }

    @FXML
    void setCreateButton() {
        createFileLabel.setText("");
        String name = createFileTextField.getText();
        if (!name.isEmpty()) {
            EbookModel.userNameFile(name);
            createFileLabel.setTextFill(Color.GREEN);
            createFileLabel.setText("Successfull created!");

        } else {
            createFileLabel.setTextFill(Color.RED);
            createFileLabel.setText("Please enter a name for your file");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       choiceBox.setItems(FXCollections.observableArrayList("Notes", "Bookmarks", "Both"));


    }

}