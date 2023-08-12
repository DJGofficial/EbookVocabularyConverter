package selfmade.ebookConverter.controller;

import javafx.stage.FileChooser;
import selfmade.ebookConverter.model.TextColour;

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
}

