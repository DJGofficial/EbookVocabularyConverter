package selfmade.ebookConverter.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import selfmade.ebookConverter.model.EbookModel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import selfmade.ebookConverter.model.EbookModel;


public class EbookView extends HBox implements Initializable {
    @FXML
    private TreeView<File> treeView;
    @FXML
    Button fileButton = new Button("Choose File");
    @FXML
    TextField fileTextField= new TextField();
    File directory = new File("/home/usr/");

    FileChooser fileChooser = new FileChooser();

    @FXML
    void selectFile(MouseEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        fileTextField.setText(file.getName());
        EbookModel.readStart(file);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("/"));

    }

}