import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxToolkit.*;

import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxRobot;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import selfmade.ebookConverter.view.EbookView;

@ExtendWith(ApplicationExtension.class)
public class EbookViewTest extends ApplicationTest {

    private EbookView ebookView;

  @BeforeEach
    public void setUp() throws Exception {
      setupStage(stage -> {
          ebookView = new EbookView();
        //stage.setScene(new Scene(ebookView));
          stage.show();
      });
    }

    /*
    @Start
    public void start(Stage stage) throws Exception {
        // Initialize your JavaFX application (you may need to modify this depending on your setup)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/your/fxml/file.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ebookView = loader.getController();
    }
     */
    @Start
    public void start() {
        // Hier kannst du deine FXML-Datei für die Ansicht initialisieren (sofern vorhanden).
        // In diesem Beispiel verwenden wir jedoch eine leere Ansicht.
    }

    // Komponententest für setChooseFileButton-Methode
    @Test
    public void testSetChooseFileButton() {
        interact(() -> {
            // Simuliere den Klick auf den "fileButton"
         //   clickOn(ebookView.fileButton);

            // Überprüfe, ob das Textfeld nicht leer ist
       //     TextField fileTextField = ebookView.getFileTextField();
         //   assertFalse(fileTextField.getText().isEmpty());
        });
    }

    // Komponententest für setCreateButton-Methode
    @Test
    public void testSetCreateButton() {
        interact(() -> {
            // Hier kannst du die Benutzeroberfläche so vorbereiten, dass sie für den Test geeignet ist
            // Zum Beispiel füge Buttons zur FlowPane hinzu, die du für den Test benötigst

            // Simuliere den Klick auf den "createButton"
         //   clickOn(ebookView.createButton);

            // Überprüfe, ob die bottomMessageLabel die Erfolgsmeldung anzeigt
        ///    assertEquals("Successfully created!", ebookView.bottomMessageLabel.getText());
        });
    }

    // Schnittstellentest für fillFlowPaneButtonClicked-Methode
    @Test
    public void testFillFlowPaneButtonClicked() {
        interact(() -> {
            // Hier kannst du die Benutzeroberfläche so vorbereiten, dass sie für den Test geeignet ist
            // Zum Beispiel füge Buttons zur FlowPane hinzu, die du für den Test benötigst

            // Simuliere den Klick auf den "fillFlowPaneButton"
       //     clickOn(ebookView.fillFlowPaneButton);

            // Überprüfe, ob die FlowPane die erwarteten Buttons enthält
       //     assertTrue(ebookView.flowPane.getChildren().size() > 0);

            // Überprüfe, ob die "doneButton" und "deleteButton" aktiviert sind
        //    assertTrue(ebookView.doneButton.isDisabled());
         //   assertTrue(ebookView.deleteButton.isDisabled());
        });
    }

    // Weitere Tests für andere Methoden in der EbookView-Klasse können nach dem gleichen Muster erstellt werden.
}
