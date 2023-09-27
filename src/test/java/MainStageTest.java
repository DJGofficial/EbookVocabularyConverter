import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import selfmade.ebookConverter.MainStage;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;
@ExtendWith(ApplicationExtension.class)
public class MainStageTest {

    private final MainStage mainStage= new MainStage();
    private Label message;
    @Start
    public void start(Stage stage) throws IOException {
        // Hier kannst du die Startmethode deiner JavaFX-Anwendung aufrufen+ste
        ;
      //  new MainStage().start(stage);
        message = new Label("Welcome!");

        stage.setScene(new Scene(new StackPane(message)));
        stage.show();
    }


    @Test
    public void testMainStage() {
        // Führe hier deine GUI-Tests aus
        // Zum Beispiel: Klicke auf GUI-Elemente, überprüfe Textfelder usw.
        // Hier ist ein einfaches Beispiel:
        System.out.println("You are here");
        //clickOn("#fileButton");
        verifyThat("#fileButton", hasText("Datei wählen"));
        // Klicke auf ein GUI-Element mit der ID "yourButtonId"
        // Führe Assertions durch, um das erwartete Verhalten zu überprüfen
        // Zum Beispiel: verifyThat("#resultLabel", hasText("Expected Text"));
    }

    @Test
    void should_click_on_button(FxRobot robot) {
        // when:
        robot.clickOn(".button");

        // then:
        verifyThat(".button", hasText("clicked!"));
    }
/*

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage(); // Schließe die JavaFX-Anwendung nach jedem Test
        release(new KeyCode[]{}); // Freigegebene Tasten (falls notwendig)
        release(new MouseButton[]{}); // Freigegebene Maustasten (falls notwendig)
    }

 */
}

