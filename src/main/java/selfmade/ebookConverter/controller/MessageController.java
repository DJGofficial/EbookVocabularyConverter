package selfmade.ebookConverter.controller;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MessageController {

    public MessageController() {
    }

    public Label showSuccessMessage(Label label, String message) {
        label.setTextFill(Color.GREEN);
        label.setText(message);
        return label;

    }

    public Label showErrorMessage(Label label, String message) {
        label.setTextFill(Color.RED);
        label.setText(message);
        return label;
    }


}

