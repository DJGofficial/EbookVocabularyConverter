package selfmade.ebookConverter.view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnkiDeckChoose  implements Initializable {
    @FXML
    AnchorPane root;

    @FXML
    ChoiceBox choiceBoxAnkiDecks= new ChoiceBox<>();

    private  ObservableList<String> deckList;

    /*
    public AnkiDeckChoose(ObservableList<String> deckList) {
        this.deckList=deckList;
    }


     */





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*
        System.out.println("Start Method AnkiDeckChoose");

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/selfmade/ebookConverter/AnkiDeckChoose.fxml"));
        } catch (IOException e) {
            System.out.println("Runtime Exception reached");
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        stage.setTitle("Anki Deck Chooser");
        stage.setScene(scene);
        stage.show();

 */
    }
}
