package selfmade.ebookConverter.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import selfmade.ebookConverter.model.TextColour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ButtonController {

    private static FileChooser fileChooser = new FileChooser();

    private static String buttonChoiceBoxStatus;

    static TextColour textColour = new TextColour();

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

            buttons.add(button);

        }

        return buttons;
    }
}

