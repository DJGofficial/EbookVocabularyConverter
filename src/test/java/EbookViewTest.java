import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.testfx.framework.junit5.Start;
import selfmade.ebookConverter.controller.FileController;
import selfmade.ebookConverter.view.EbookView;

import java.io.IOException;

@ExtendWith({ApplicationExtension.class, MockitoExtension.class})
public class EbookViewTest {

    @Mock
    private FileController fileController;

    @InjectMocks
    private EbookView ebookView;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/E_Little/IdeaProjects/EbookVocabularyConverter/src/main/resources/selfmade/ebookConverter/MainStage.fxml"));
       AnchorPane anchorPane = loader.load();
        ebookView = loader.getController();
       // loader.setRoot(new AnchorPane());
       // Parent root= loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    // UI Component Test with TestFX
    @Test
    public void testFillFlowPaneButtonClicked(FxRobot robot) {
        robot.clickOn("#fillFlowPaneButton");
        assertFalse(ebookView.getTestFlowPane().getChildren().isEmpty(), "FlowPane should not be empty after click");
    }

    // Simple Unit Test
    @Test
    public void testSetMessageLabel() {
        String message = "Test message";
        ebookView.setMessageLabel(message);
        assertEquals(message, ebookView.getMessageLabel().getText(), "Message label should contain test text");
    }

    // Integration Test with Mockito
    @Test
    public void testFileControllerInvocation(FxRobot robot) throws IOException {
        when(fileController.chooseFile()).thenReturn("test-file-path");
        robot.clickOn("#fileButton");
        verify(fileController, times(1)).chooseFile();
        assertEquals("test-file-path", ebookView.getTestFileTextField().getText(), "Text field should contain simulated file path");
    }

/*
    // Schnittstellentest
    @Test
    public void testFillFlowPaneButtonClicked(FxRobot robot) {
        robot.clickOn("#fillFlowPaneButton"); // die fx:id des Buttons in der FXML-Datei
        FlowPane flowPane = ebookView.getTestFlowPane();
        assertFalse(flowPane.getChildren().isEmpty(), "FlowPane sollte nach dem Klick nicht leer sein");
    }



    // Komponententest
    @Test
    public void testSetMessageLabel() {
        String message = "Test message";
        ebookView.setMessageLabel(message);
        assertEquals(message, ebookView.getMessageLabel().getText(), "Das Nachrichtenlabel sollte den Testtext enthalten");
    }
    */

/*
    // Black-Box-Test
    @Test
    public void testFileControllerInvocation(FxRobot robot) throws IOException {
        when(fileController.chooseFile()).thenReturn("test-file-path");

        robot.clickOn("#fileButton");

        verify(fileController, times(1)).chooseFile();
        assertEquals("test-file-path", ebookView.getTestFileTextField().getText(), "Das Textfeld sollte den simulierten Dateipfad enthalten");
    }

 */
}
