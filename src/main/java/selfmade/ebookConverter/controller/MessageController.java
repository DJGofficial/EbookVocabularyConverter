package selfmade.ebookConverter.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import selfmade.ebookConverter.view.EbookView;

public class MessageController {


    EbookView ebookView;
    public MessageController(EbookView ebookView) {
        this.ebookView = ebookView;
    }



    public Label showSuccessMessage(Label label,String message) {

        label.setTextFill(Color.GREEN);
        label.setText(message);
        return label;

    }

    public Label showErrorMessage(Label label,String message) {

        label.setTextFill(Color.RED);
        label.setText(message);
        return label;
    }


}

