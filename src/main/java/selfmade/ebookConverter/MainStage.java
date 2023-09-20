package selfmade.ebookConverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainStage extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource("MainStage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Ebook Vocabulary Converter");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(true);
    }

    public void showNewStage() {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AnkiDeckChoose.fxml"));
        Parent secondRoot = null;
        try {
            secondRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene secondScene = new Scene(secondRoot);
        newStage.setScene(secondScene);
        newStage.setTitle("Bitte Deck wählen");
        newStage.initStyle(StageStyle.DECORATED);
        newStage.setResizable(false);
        newStage.show();

    }
/*
  public static void main(String[] args) {
     MainStage.launch();
    }
 */



}