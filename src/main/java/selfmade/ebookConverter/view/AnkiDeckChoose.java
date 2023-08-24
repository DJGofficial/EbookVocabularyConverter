package selfmade.ebookConverter.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
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
import selfmade.ebookConverter.model.ChoiceBoxItems;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnkiDeckChoose implements Initializable {
    @FXML
    AnchorPane root;
    @FXML
    ChoiceBox<String> choiceBoxAnkiDecks = new ChoiceBox<>();

@FXML
    private static ObservableList<String> deckListLocal= FXCollections.observableArrayList();

    MainStage mainStage = new MainStage();


    // EbookView ebookView;

/*
    public AnkiDeckChoose(ObservableList<String> deckList) {
    this.deckListLocal=deckList;
        choiceBoxAnkiDecks.getItems().addAll(deckListLocal);
    }


 */

    @FXML
    public void callWindowAddDeckList(ObservableList<String> deckList) {
       this.deckListLocal.addAll(deckList);
      /*  for (String item : deckListLocal) {
            System.out.println("Item "+item);
           choiceBoxAnkiDecks.getItems().add(item);
        }

       */
      //  choiceBoxAnkiDecks.getItems().addAll(deckList.stream().toList());
      //  this.deckList = deckList;
        mainStage.showNewStage();
      /*  for (String item : deckList) {
            System.out.println("Item "+item);
            choiceBoxAnkiDecks.getItems().add(item);
        }
        //choiceBoxAnkiDecks = new ChoiceBox<>(deckList);

        mainStage.showNewStage();


       */
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBoxAnkiDecks.getItems().addAll(this.deckListLocal);

        // choiceBoxAnkiDecks.getItems().addAll("Option A", "Option B");
       /* if (choiceBoxAnkiDecks != null && deckListLocal != null) {
            choiceBoxAnkiDecks.setItems(deckListLocal);

            for (String item : deckListLocal) {
                System.out.println("Item " + item);
            }
        } else {
            System.out.println("ChoiceBox or deckListLocal is null");
        }

        */
 /*choiceBoxAnkiDecks.getItems().addAll(deckListLocal);

        for (String item : deckListLocal) {
            System.out.println("Item " + item);
         //   choiceBoxAnkiDecks.getItems().add(item);
        }

  */


        //  System.out.println("You are here");
        //  choiceBoxAnkiDecks.setItems(this.deckList);
        //choiceBoxAnkiDecks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        //      });

        //    choiceBoxAnkiDecks.setItems(deckList);
    }
}



