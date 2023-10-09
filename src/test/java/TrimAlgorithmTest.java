
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selfmade.ebookConverter.controller.TrimAlgorithm;
import selfmade.ebookConverter.view.EbookView;

import static org.junit.jupiter.api.Assertions.*;

class TrimAlgorithmTest {

    private TrimAlgorithm trimAlgorithm;
    private EbookView mockEbookView;
    private static FlowPane flowPane = new FlowPane();

    @BeforeAll
    public static void setUpClass() {
        // Initialisiert das JavaFX Toolkit
        Platform.startup(() -> {
        });

        // Erstellen und Hinzufügen von ToggleButtons zum FlowPane
        for (int i = 1; i <= 20; i++) {
            ToggleButton toggleButton = new ToggleButton("Button " + i);
            flowPane.getChildren().add(toggleButton);
        }
    }

    @BeforeEach
    void setUp() {
        ObservableList<Node> mockContent = FXCollections.observableArrayList();
        // Fügen Sie hier Mock-Node-Objekte hinzu, ähnlich zu dem echten `contentList`
        mockEbookView = new EbookView(); // Wenn Sie einen Mock verwenden möchten, ändern Sie dies entsprechend
        trimAlgorithm = new TrimAlgorithm(mockContent, mockEbookView);
    }

    @Test
    void testCheckContent() {
        boolean[] result = trimAlgorithm.checkContent();
        // Überprüfen Sie hier, ob die Methode das erwartete Ergebnis liefert
        assertTrue(result[0]);  // Anpassen je nach erwartetem Ergebnis
        assertFalse(result[1]);  // Anpassen je nach erwartetem Ergebnis
    }

    @Test
    void testGetContentToTextFragments() {
        FlowPane flowPane = new FlowPane();
        trimAlgorithm.getContentToTextFragments();
        // Fügen Sie Überprüfungen hinzu, um zu sehen, ob `texts` wie erwartet gefüllt wird
        assertEquals("erwarteterText", trimAlgorithm.getVocabulary());  // Beispiel
    }

    @Test
    void testGetMarkings() {
        trimAlgorithm.getMarkings();
        assertEquals("erwarteterEndMark", trimAlgorithm.getEndMark());
        assertEquals("erwarteterVocabulary", trimAlgorithm.getVocabulary());
        assertEquals("erwarteterTitle", trimAlgorithm.getTitle());
        assertEquals("erwarteterType", trimAlgorithm.getType());
    }

    // Fügen Sie mehr Tests hinzu, um die verschiedenen Methoden und Grenzfälle zu testen
}

/*import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import org.testfx.framework.junit5.ApplicationTest;
import selfmade.ebookConverter.MainStage;
import selfmade.ebookConverter.controller.TrimAlgorithm;
import selfmade.ebookConverter.view.EbookView;

import java.util.Arrays;

public class TrimAlgorithmTest {

    private TrimAlgorithm trimAlgorithm;
    private ObservableList<Node> contentList;

    @BeforeAll
    public static void setupSpec() throws Exception {
        ApplicationTest.launch(MainStage.class);
    }

    @BeforeEach
    void setUp() {
        ToggleButton endMarkBtn = new ToggleButton("End");
        endMarkBtn.setStyle("-fx-background-color: #D9FFD9");

        ToggleButton vocabularyBtn = new ToggleButton("Vocabulary");
        vocabularyBtn.setStyle("-fx-background-color: #FFD9D9");

        ToggleButton titleBtn = new ToggleButton("Title");
        titleBtn.setStyle("-fx-background-color: #FFFFD9");

        ToggleButton typeBtn = new ToggleButton("Type");
        typeBtn.setStyle("-fx-background-color: #D9E5FF");


        contentList = FXCollections.observableArrayList(Arrays.asList(endMarkBtn, vocabularyBtn, titleBtn, typeBtn));
        trimAlgorithm = new TrimAlgorithm(contentList, new EbookView()); // Hier könnte ein Mock von EbookView benutzt werden

        //      contentList = FXCollections.observableArrayList(Arrays.asList(endMarkBtn));
//        trimAlgorithm = new TrimAlgorithm(contentList, new EbookView()); // Hier könnte ein Mock von EbookView benutzt werden
    }

    @Test
    void testCheckContent() {
        // boolean[] result = trimAlgorithm.checkContent();
        //  assertTrue(result[0]);
        //  assertTrue(result[1]);
    }


    @Test
    void testGetContentToTextFragments() {
        trimAlgorithm.getContentToTextFragments();
        // Verifizieren Sie, dass die texts ArrayList die erwarteten Werte hat.
    }

    @Test
    void testGetMarkings() {
        trimAlgorithm.getContentToTextFragments();
        trimAlgorithm.getMarkings();
        assertEquals("End", trimAlgorithm.getEndMark());
        assertEquals("Vocabulary", trimAlgorithm.getVocabulary());
        assertEquals("Title", trimAlgorithm.getTitle());
        assertEquals("Type", trimAlgorithm.getType());
    }

    // Weitere Tests können für andere Methoden hinzugefügt werden.

}



 */
