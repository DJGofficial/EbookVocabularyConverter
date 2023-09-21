package selfmade.ebookConverter.controller;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import selfmade.ebookConverter.model.TextColour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ButtonController {

    private static FileChooser fileChooser = new FileChooser();
    private static String buttonChoiceBoxStatus;

    public ButtonController() {
    }

    public static void setButtonChoiceBoxStatus(String value) {
        buttonChoiceBoxStatus = value;
    }

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