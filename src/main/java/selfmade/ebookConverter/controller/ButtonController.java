package selfmade.ebookConverter.controller;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import selfmade.ebookConverter.model.TextColour;
import selfmade.ebookConverter.view.EbookView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ButtonController {

    private static FileChooser fileChooser = new FileChooser();

    private static String buttonChoiceBoxStatus;

    static TextColour textColour = new TextColour();

    EbookView ebookView;

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

    public List<Button> createVocButton(ArrayList<String> vocList) {
        List<Button> buttons = new ArrayList<>();
        for (String vocabulary : vocList) {
            Button button = new Button(vocabulary);
            button.setStyle("-fx-background-color: #B0C4DE;; -fx-text-fill: white;");
            button.setBorder(createStyledButton());
            buttons.add(button);

        }

        return buttons;
    }

    public List<Button> createTranslatedButton(FlowPane flowPane, HashMap<String, String> transVocList) {
        List<Button> buttons = new ArrayList<>();
        for (Map.Entry<String, String> entry : transVocList.entrySet()) {
            Button button = new Button(entry.getKey() + " " + entry.getValue());
            button.setStyle("-fx-background-color: #B0C4DE;; -fx-text-fill: white;");
            button.setBorder(createStyledButton());
            button.setOnAction(event -> {
                Button clickedButton = (Button) event.getSource();
                flowPane.getChildren().remove(clickedButton);
            });

            buttons.add(button);

        }

        return buttons;
    }

    public Border createStyledButton() {
        return new Border(
                new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        null,
                        new BorderWidths(2)
                )
        );
    }
}