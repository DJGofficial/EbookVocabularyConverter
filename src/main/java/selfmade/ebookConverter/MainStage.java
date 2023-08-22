package selfmade.ebookConverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import selfmade.ebookConverter.view.AnkiDeckChoose;


import java.io.IOException;

public class MainStage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource("MainStage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Ebook Vocabulary Converter");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AnkiDeckChoose.fxml"));
        Parent secondRoot = loader.load();

        Scene secondScene= new Scene(secondRoot);
        newStage.setScene(secondScene);
        newStage.setTitle("Neues Fenster");
        newStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}