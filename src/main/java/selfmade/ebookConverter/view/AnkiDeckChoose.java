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
import selfmade.ebookConverter.MainStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnkiDeckChoose implements Initializable {
    @FXML
    AnchorPane root;
    @FXML
    ChoiceBox choiceBoxAnkiDecks = new ChoiceBox<>();

    private ObservableList<String> deckList;

    MainStage mainStage = new MainStage();
    // EbookView ebookView;


    /*public AnkiDeckChoose(ObservableList<String> deckList) {
        this.deckList=deckList;
        ebookView=new EbookView();
    }

     */

    public void callWindowAddDeckList(ObservableList<String> deckList) {
        this.deckList = deckList;
       // choiceBoxAnkiDecks.setItems(deckList);
        mainStage.showNewStage();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAnkiDecks.setItems(deckList);
    }
}



