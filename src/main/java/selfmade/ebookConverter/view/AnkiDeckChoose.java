package selfmade.ebookConverter.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import selfmade.ebookConverter.MainStage;
import selfmade.ebookConverter.connection.AnkiConnection;
import selfmade.ebookConverter.controller.AnkiController;
import selfmade.ebookConverter.controller.MessageController;
import selfmade.ebookConverter.model.ChoiceBoxItems;
import selfmade.ebookConverter.model.TextAttributesObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AnkiDeckChoose implements Initializable {

    @FXML
    private Button deckConfirmButton;

    @FXML
    private AnchorPane root;

    @FXML
    private Text text;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    ChoiceBox<String> choiceBoxAnkiDecks = new ChoiceBox<>();


    private static ObservableList<String> deckListLocal = FXCollections.observableArrayList();
    @FXML
    MainStage mainStage = new MainStage();
    AnkiController ankiController;

    @FXML
    AnkiConnection ankiConnection;
    TextAttributesObject textAttributesObject = new TextAttributesObject<>();
    // EbookView ebookView= new EbookView();

    @FXML
    public void callWindowAddDeckList(ObservableList<String> deckList) {
        this.deckListLocal.addAll(deckList);
        mainStage.showNewStage();
    }

    @FXML
    private void deckConfirmButtonClicked() throws IOException {
        List<String> texter = new ArrayList<>();
               texter=ankiController.createAndAddCards(choiceBoxAnkiDecks.getValue(), textAttributesObject.getTranslatedMap());
        for (String entry : texter) {
            System.out.println(entry);
            textArea.setText(entry + "\n");
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (String entry : texter) {
            stringBuilder.append(entry).append("\n");
        }

        textArea.setText(stringBuilder.toString());
        System.out.println("Size "+texter.size());
        // Stage currentStage = (Stage) deckConfirmButton.getScene().getWindow();
        // currentStage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAnkiDecks.getItems().addAll(this.deckListLocal);
        ankiController = new AnkiController();

    }
}




