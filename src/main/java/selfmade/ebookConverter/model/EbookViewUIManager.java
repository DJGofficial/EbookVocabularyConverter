package selfmade.ebookConverter.model;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import selfmade.ebookConverter.controller.MessageController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Verwaltet die UI-Elemente in der E-Book-Ansicht.
 */
public class EbookViewUIManager {

    private final TextField fileTextField;
    private final Button fillFlowPaneButton;
    private final FlowPane flowPane;
    private final Label bottomMessageLabel;
    private final Label messageLabel;
    private MessageController messageController;

    /**
     * Konstruktor, initialisiert die UI-Elemente.
     *
     * @param fileTextField      TextField für die Dateiauswahl.
     * @param fillFlowPaneButton Button zum Befüllen des FlowPanes.
     * @param flowPane           Container für dynamisch hinzugefügte Elemente.
     * @param bottomMessageLabel Label für Nachrichten am unteren Rand.
     * @param messageLabel       Label für allgemeine Nachrichten.
     */
    public EbookViewUIManager(TextField fileTextField,
                              Button fillFlowPaneButton,
                              FlowPane flowPane,
                              Label bottomMessageLabel,
                              Label messageLabel) {
        this.fileTextField = fileTextField;
        this.fillFlowPaneButton = fillFlowPaneButton;
        this.flowPane = flowPane;
        this.bottomMessageLabel = bottomMessageLabel;
        this.messageLabel = messageLabel;
        this.messageController = new MessageController();
    }

    /**
     * Aktualisiert den Text im fileTextField und aktiviert den fillFlowPaneButton.
     *
     * @param text Der anzuzeigende Text.
     */
    public void updateFileTextField(String text) {
        this.fileTextField.setText(text);
        this.fillFlowPaneButton.setDisable(false);
    }

    /**
     * @return Eine Liste der Texte von ToggleButtons im FlowPane.
     */
    public ArrayList<String> getToggleButtonTextFromFlowPane() {
        ArrayList<String> outputList = new ArrayList<>();
        for (Node node : flowPane.getChildren()) {
            if (node instanceof ToggleButton) {
                ToggleButton button = (ToggleButton) node;
                outputList.add(button.getText());
            }
        }
        return outputList;
    }

    /**
     * Zeigt eine Erfolgs- oder Fehlermeldung an.
     *
     * @param success Gibt an, ob die Aktion erfolgreich war.
     * @param message Die anzuzeigende Nachricht.
     */
    public void setBottomMessage(boolean success, String message) {
        if (success) {
            messageController.showSuccessMessage(messageLabel, message);
        } else {
            messageController.showErrorMessage(messageLabel, message);
        }
    }

    /**
     * @return Das Label für die Nachricht am unteren Rand.
     */
    public Label getBottomMessage() {
        return bottomMessageLabel;
    }

    /**
     * Zeigt eine Fehlermeldung im messageLabel an.
     *
     * @param message Die anzuzeigende Nachricht.
     */
    public void setMessageLabel(String message) {
        messageController.showErrorMessage(messageLabel, message);
    }

    /**
     * Zeigt Übersetzungen im FlowPane an.
     *
     * @param translations Ein HashMap der Übersetzungen, wobei der Schlüssel der Originaltext und der Wert die Übersetzung ist.
     */
    public void showTranslations(HashMap<String, String> translations) {
        flowPane.getChildren().clear();  // Löschen des vorherigen Inhalts
        for (Map.Entry<String, String> entry : translations.entrySet()) {
            Label originalText = new Label(entry.getKey());
            Label translatedText = new Label(entry.getValue());

            VBox vBox = new VBox(originalText, translatedText); // Beide Labels in einem VBox
            flowPane.getChildren().add(vBox); // Fügen Sie die VBox zum FlowPane hinzu
        }
    }

    /**
     * Aktiviert den fillFlowPaneButton.
     */
    public void enableFillFlowPaneButton() {
        fillFlowPaneButton.setDisable(false);
    }

    /**
     * Leert den Inhalt des FlowPane.
     */
    public void clearFlowPane() {
        flowPane.getChildren().clear();
    }
}



