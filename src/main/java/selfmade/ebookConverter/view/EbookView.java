package selfmade.ebookConverter.view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import javafx.scene.layout.HBox;
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
    private File file;

    //Methods
    @FXML
    void selectFile() throws IOException {
        fileTextField.setText(EbookModel.chooseFile(file));

    }

    @FXML
    void setCreateButton() {
       String name= createFileTextField.getText();
       if( EbookModel.userNameFile(name));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}