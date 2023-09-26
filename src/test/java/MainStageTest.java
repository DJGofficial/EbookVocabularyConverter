import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import selfmade.ebookConverter.MainStage;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;

public class MainStageTest extends ApplicationTest {

    public static void main(String[] args) {
    start();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Hier kannst du die Startmethode deiner JavaFX-Anwendung aufrufen
        new MainStage().start(stage);
    }


    @Test
    public void testMainStage() {
        // Führe hier deine GUI-Tests aus
        // Zum Beispiel: Klicke auf GUI-Elemente, überprüfe Textfelder usw.
        // Hier ist ein einfaches Beispiel:
        clickOn("#fileButton");
        verifyThat("#fileButton", hasText("Datei wählen"));
        // Klicke auf ein GUI-Element mit der ID "yourButtonId"
        // Führe Assertions durch, um das erwartete Verhalten zu überprüfen
        // Zum Beispiel: verifyThat("#resultLabel", hasText("Expected Text"));
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

