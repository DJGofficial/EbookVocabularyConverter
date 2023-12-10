package selfmade.ebookConverter.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import selfmade.ebookConverter.MainStage;
import selfmade.ebookConverter.controller.AnkiController;
import selfmade.ebookConverter.model.TextAttributesObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AnkiDeckChoose implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    ChoiceBox<String> choiceBoxAnkiDecks = new ChoiceBox<>();
    private static ObservableList<String> deckListLocal = FXCollections.observableArrayList();
    @FXML
    MainStage mainStage = new MainStage();
    AnkiController ankiController;
    TextAttributesObject textAttributesObject;

    @FXML
    public void callWindowAddDeckList(ObservableList<String> deckList) {
        this.deckListLocal.addAll(deckList);
        mainStage.showNewStage();
    }

    @FXML
    private void deckConfirmButtonClicked() throws IOException {
        List<String> texter = new ArrayList<>();
        texter = ankiController.createAndAddCards(choiceBoxAnkiDecks.getValue(), textAttributesObject.getTranslatedMap());
        for (String entry : texter) {
            System.out.println(entry);
            textArea.setText(entry + "\n");
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (String entry : texter) {
            stringBuilder.append(entry).append("\n");
        }
        textArea.setText(stringBuilder.toString());
        System.out.println("Size " + texter.size());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAnkiDecks.getItems().addAll(this.deckListLocal);
        ankiController = new AnkiController();

    }
}




