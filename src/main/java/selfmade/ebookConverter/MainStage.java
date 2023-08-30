package selfmade.ebookConverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Locale;
import java.util.ResourceBundle;


import java.io.IOException;

public class MainStage extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource("MainStage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
     // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Ebook Vocabulary Converter");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void showNewStage(){
        Stage newStage= new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AnkiDeckChoose.fxml"));
        //? Parent secondRoot = loader.load();
        Parent secondRoot = null;
        try {
            secondRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene secondScene= new Scene(secondRoot);
        //secondScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        newStage.setScene(secondScene);
        newStage.setTitle("Bitte Deck wählen");
        newStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}