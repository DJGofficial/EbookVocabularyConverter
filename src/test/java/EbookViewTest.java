import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import javafx.stage.Stage;
import selfmade.ebookConverter.view.EbookView;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxToolkit.*;

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

    @Start
    public void start(Stage stage) throws Exception {
        // Initialize your JavaFX application (you may need to modify this depending on your setup)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/E_Little/IdeaProjects/EbookVocabularyConverter/src/main/resources/selfmade/ebookConverter/MainStage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ebookView = loader.getController();
    }

    @Test
    public void testSetMessageLabel(FxRobot robot) {
        // Call the setMessageLabel method with the desired message
        String message = "Ein Test";
        ebookView.setMessageLabel(message);

        // Now, you can assert something based on the expected behavior
        Label messageLabel = ebookView.getMessageLabel(); // Replace with the actual method to get the label
        assertNotNull(messageLabel); // Assuming the label exists
        assertEquals(message, messageLabel.getText());
    }

/*
    @Test
    public void testSetCreateButton(FxRobot robot) {
        // Simulate clicking on the "createButton"
        robot.clickOn(ebookView.getCreateButton());

        // Now, you can assert something based on the expected behavior
        assertEquals("Successfully created!", ebookView.getBottomMessageLabel().getText());
    }

    @Test
    public void testFillFlowPaneButtonClicked(FxRobot robot) {
        // Simulate clicking on the "fillFlowPaneButton"
        robot.clickOn(ebookView.getFillFlowPaneButton());

        // Now, you can assert something based on the expected behavior
        assertTrue(ebookView.getFlowPane().getChildren().size() > 0);

        // You can add more assertions as needed
    }

    // Add more test methods for other functionalities of your EbookView class as needed.

 */
}
