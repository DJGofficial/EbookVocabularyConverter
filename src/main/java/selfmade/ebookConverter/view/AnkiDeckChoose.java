package selfmade.ebookConverter.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import selfmade.ebookConverter.MainStage;
import selfmade.ebookConverter.connection.AnkiConnection;
import selfmade.ebookConverter.controller.AnkiController;
import selfmade.ebookConverter.model.ChoiceBoxItems;
import selfmade.ebookConverter.model.TextAttributesObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnkiDeckChoose implements Initializable {
    @FXML
    AnchorPane root;
    @FXML
    ChoiceBox<String> choiceBoxAnkiDecks = new ChoiceBox<>();

    @FXML
    Button deckConfirmButton= new Button();

    private static ObservableList<String> deckListLocal = FXCollections.observableArrayList();

    MainStage mainStage = new MainStage();

    AnkiConnection ankiConnection;
    AnkiController ankiController = new AnkiController();
    TextAttributesObject textAttributesObject = new TextAttributesObject<>();
    //EbookView ebookView;

    @FXML
    public void callWindowAddDeckList(ObservableList<String> deckList) {
        this.deckListLocal.addAll(deckList);
        mainStage.showNewStage();

    }

    @FXML
    private void deckConfirmButtonClicked() {
        ankiController.createAndAddCards(ankiConnection, choiceBoxAnkiDecks.getValue(), textAttributesObject.getTranslatedMap());
        Stage currentStage = (Stage) deckConfirmButton.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAnkiDecks.getItems().addAll(this.deckListLocal);

    }
}



