package selfmade.ebookConverter.controller;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import selfmade.ebookConverter.view.EbookView;

public class MessageController {
    /*public MessageController(EbookView ebookView) {
        this.ebookView = ebookView;
    }

     */

    public Label showSuccessMessage(Label label,String message) {
        Label retLabel = new Label();
        retLabel.setTextFill(Color.GREEN);
        retLabel.setText(message);
        return retLabel;

    }

    public Label showErrorMessage(Label label,String message) {
        Label retLabel = new Label();
        retLabel.setTextFill(Color.RED);
        retLabel.setText(message);
        return retLabel;
    }
}

