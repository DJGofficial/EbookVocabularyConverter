package selfmade.ebookConverter.controller;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import selfmade.ebookConverter.model.TextColour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Verwaltet die Erstellung und das Styling von Buttons innerhalb der Anwendung.
 */
public class ButtonController {

    private static FileChooser fileChooser = new FileChooser();
    private static String buttonChoiceBoxStatus;

    /**
     * Standardkonstruktor für ButtonController.
     */
    public ButtonController() {
    }

    /**
     * Setzt den aktuellen Wert für die Button-Auswahlbox.
     *
     * @param value Der zu setzende Wert.
     */
    public static void setButtonChoiceBoxStatus(String value) {
        buttonChoiceBoxStatus = value;
    }

    /**
     * Liefert die Farbe für einen Button basierend auf dem aktuellen Wert der Auswahlbox.
     *
     * @return Die Hexadezimal-Farbe als Zeichenkette oder null, wenn keine Übereinstimmung gefunden wird.
     */
    public String getButtonColour() {
        if (buttonChoiceBoxStatus != null) {
            for (TextColour tC : TextColour.createTextColourList()) {
                if (buttonChoiceBoxStatus.equals(tC.getName())) {
                    return tC.getColour();
                }
            }
        }
        return null;
    }

    /**
     * Erstellt eine Liste von Buttons basierend auf einer gegebenen Liste von Vokabeln und fügt sie zu einem FlowPane hinzu.
     *
     * @param flowPane Das FlowPane, in das die Buttons eingefügt werden sollen.
     * @param vocList  Die Liste von Vokabeln.
     * @return Eine Liste der erstellten Buttons.
     */
    public List<Button> createVocButton(FlowPane flowPane, ArrayList<String> vocList) {
        List<Button> buttons = new ArrayList<>();
        for (String vocabulary : vocList) {
            Button button = new Button(vocabulary);
            button.setStyle(createStyledButton());
            flowPane.getChildren().add(button);
            button.setOnAction(event -> {
                Button clickedButton = (Button) event.getSource();
                flowPane.getChildren().remove(clickedButton);

            });
        }
        return buttons;
    }

    /**
     * Erstellt eine Liste von übersetzten Buttons basierend auf einer gegebenen Hashmap und fügt sie zu einem FlowPane hinzu.
     *
     * @param flowPane     Das FlowPane, in das die Buttons eingefügt werden sollen.
     * @param transVocList Die HashMap der übersetzten Vokabeln.
     * @return Eine Liste der erstellten Buttons.
     */
    public List<Button> createTranslatedButton(FlowPane flowPane, HashMap<String, String> transVocList) {
        List<Button> buttons = new ArrayList<>();

        for (Map.Entry<String, String> entry : transVocList.entrySet()) {
            Button button = new Button(entry.getKey() + " " + entry.getValue());//createStyledButton();
            button.setStyle(createStyledButton());

            button.setOnAction(event -> {
                Button clickedButton = (Button) event.getSource();
                flowPane.getChildren().remove(clickedButton);
            });
            buttons.add(button);
        }
        return buttons;
    }

    /**
     * Erstellt den Style für einen Button und gibt ihn als Zeichenkette zurück.
     *
     * @return Die Style-Zeichenkette für den Button.
     */
    public String createStyledButton() {
        return "-fx-background-radius: 50;" +
                "-fx-border-radius: 15px;" +
                "-fx-background-color: #B0C4DE;" +
                "-fx-text-fill: black;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: Grey;" +
                "-fx-border-width: 2px;" +
                "-fx-background-insets: 2px;";
    }


}