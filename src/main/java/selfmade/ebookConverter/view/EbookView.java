package selfmade.ebookConverter.view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import selfmade.ebookConverter.model.EbookModel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class EbookView extends HBox implements Initializable {

    @FXML
    Button fileButton = new Button("Choose File");

    @FXML
    Button createButton = new Button();
    @FXML
    Button ankiButton = new Button();
    @FXML
    TextField fileTextField = new TextField();

    FileChooser fileChooser = new FileChooser();

    @FXML
    void selectFile() {
        File file = fileChooser.showOpenDialog(new Stage());
        fileTextField.setText(file.getName());
        EbookModel.readStart(file);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("/"));

    }

}