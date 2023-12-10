package selfmade.ebookConverter.controller;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import selfmade.ebookConverter.controller.MessageController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EbookViewUIManager {

    private TextField fileTextField;
    private Button fillFlowPaneButton;
    private FlowPane flowPane;
    private Label bottomMessageLabel;
    private Label messageLabel;
    private MessageController messageController;

    public EbookViewUIManager() {

        this.messageController = new MessageController();
    }

    public void initializeUIComponents(TextField fileTextField,
                                       Button fillFlowPaneButton,
                                       FlowPane flowPane,
                                       Label bottomMessageLabel,
                                       Label messageLabel) {
        this.fileTextField = fileTextField;
        this.fillFlowPaneButton = fillFlowPaneButton;
        this.flowPane = flowPane;
        this.bottomMessageLabel = bottomMessageLabel;
        this.messageLabel = messageLabel;
    }

    public void updateFileTextField(String text) {
        this.fileTextField.setText(text);
        this.fillFlowPaneButton.setDisable(false);
    }


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

    public void setBottomMessage(boolean success, String message) {
        if (success) {
            messageController.showSuccessMessage(messageLabel, message);
        } else {
            messageController.showErrorMessage(messageLabel, message);
        }
    }


    public void setMessageLabel(String message) {
        messageController.showErrorMessage(messageLabel, message);
    }

    public void showTranslations(HashMap<String, String> translations) {
        flowPane.getChildren().clear();
        for (Map.Entry<String, String> entry : translations.entrySet()) {
            Label originalText = new Label(entry.getKey());
            Label translatedText = new Label(entry.getValue());

            VBox vBox = new VBox(originalText, translatedText);
            flowPane.getChildren().add(vBox);
        }
    }

    public void enableFillFlowPaneButton() {
        fillFlowPaneButton.setDisable(false);
    }

    public void clearFlowPane() {
        flowPane.getChildren().clear();
    }
}



