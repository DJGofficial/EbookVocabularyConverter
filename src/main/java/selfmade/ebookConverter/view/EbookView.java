package selfmade.ebookConverter.view;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import selfmade.ebookConverter.controller.EbookController;
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

    String choiceBoxOptions[];
    @FXML
    ChoiceBox choiceBox = new ChoiceBox();
    @FXML
    TextFlow textFlow = new TextFlow();

    //Methods
    @FXML
    void setChooseFileButton() throws IOException {
        fileTextField.setText(EbookController.chooseFile());

    }

    @FXML
    void setCreateButton() {
        createFileLabel.setText("");
        String name = createFileTextField.getText();
        if (!name.isEmpty()) {
            EbookController.userNameFile(name);
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

    public void appendToTextFlow(String text) {
      createFileTextField.setText(text);
        Text newText = new Text(text);
        System.out.print(text);
        textFlow.getChildren().add(newText);
    }
}