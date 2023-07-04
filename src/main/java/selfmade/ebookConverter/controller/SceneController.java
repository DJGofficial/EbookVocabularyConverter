package selfmade.ebookConverter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public static void switchScene(ActionEvent event) throws IOException{
        root= FXMLLoader.load(SceneController.class.getResource("src/main/resources/selfmade/ebookConverter/OutputViewBottom.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
