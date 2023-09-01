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
import java.util.ResourceBundle;

public class AnkiDeckChoose implements Initializable {
    @FXML
    AnchorPane root;
    @FXML
    ChoiceBox<String> choiceBoxAnkiDecks = new ChoiceBox<>();
    @FXML
    private TextField textField;//= new TextField();
  //  static TextFlow textFlow = new TextFlow();

    @FXML
    Button deckConfirmButton = new Button();

    private static ObservableList<String> deckListLocal = FXCollections.observableArrayList();
    @FXML
    MainStage mainStage = new MainStage();
    @FXML
    AnkiController ankiController = new AnkiController();

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
       // textFlow.getChildren().add("Endlich klappt es");
        ankiController.createAndAddCards(choiceBoxAnkiDecks.getValue(), textAttributesObject.getTranslatedMap());
        // Stage currentStage = (Stage) deckConfirmButton.getScene().getWindow();
        // currentStage.close();
    }

    @FXML
    public  void setTextArea(String message) throws IOException {
        System.out.println("Message " + message);
       /* Text text = new Text();
        String newText = message;
        String currentText = text.getText();

        // Wenn der TextArea-Text nicht leer ist, füge einen Zeilenumbruch hinzu
        if (!currentText.isEmpty()) {
            currentText += "\n";
        }

        // Füge den neuen Text hinzu
        currentText += newText;
        text.setText(currentText);
        // Setze den aktualisierten Text zurück
        textFlow.getChildren().add(text);

        */
        textField.setText(message);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAnkiDecks.getItems().addAll(this.deckListLocal);
        textField= new TextField("Ratatatatata");
    }
//AnkiDeckChoose ankiDeckChoose= new AnkiDeckChoose();
    }




