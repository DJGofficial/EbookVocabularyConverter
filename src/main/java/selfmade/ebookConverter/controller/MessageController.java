package selfmade.ebookConverter.controller;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * Controller-Klasse für die Anzeige von Nachrichten in der Benutzeroberfläche.
 * Ermöglicht die Anzeige von Erfolgs- und Fehlermeldungen.
 */
public class MessageController {

    /**
     * Standardkonstruktor für den MessageController.
     */
    public MessageController() {
    }

    /**
     * Zeigt eine Erfolgsmeldung an, indem sie das übergebene Label mit grüner Schrift färbt
     * und den übergebenen Nachrichtentext setzt.
     *
     * @param label   Das Label, in dem die Nachricht angezeigt werden soll.
     * @param message Der anzuzeigende Nachrichtentext.
     * @return Das aktualisierte Label.
     */
    public Label showSuccessMessage(Label label, String message) {
        label.setTextFill(Color.GREEN);
        label.setText(message);
        return label;

    }

    /**
     * Zeigt eine Fehlermeldung an, indem sie das übergebene Label mit roter Schrift färbt
     * und den übergebenen Nachrichtentext setzt.
     *
     * @param label   Das Label, in dem die Nachricht angezeigt werden soll.
     * @param message Der anzuzeigende Nachrichtentext.
     * @return Das aktualisierte Label.
     */
    public Label showErrorMessage(Label label, String message) {
        label.setTextFill(Color.RED);
        label.setText(message);
        return label;
    }


}

