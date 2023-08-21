package selfmade.ebookConverter.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;


import javafx.scene.layout.FlowPane;

import javafx.scene.paint.Color;

import javafx.stage.Stage;
import selfmade.ebookConverter.connection.AnkiConnection;
import selfmade.ebookConverter.connection.GoogleTranslateAPIConnection;
import selfmade.ebookConverter.controller.FileController;
import selfmade.ebookConverter.controller.TrimAlgorithm;
import selfmade.ebookConverter.model.ChoiceBoxItems;
import selfmade.ebookConverter.controller.ButtonController;


import java.io.IOException;
import java.net.URL;
import java.util.*;


public class EbookView implements Initializable {

    @FXML
    Button fileButton, ankiButton, doneButton, createButton, translateButton;
    @FXML
    TextField fileTextField, createFileTextField;
    @FXML
    ChoiceBox<String> rootChoiceBox, optionChoiceBox, fieldsChoiceBox;
    @FXML
    ChoiceBoxItems choiceBoxItems;
    @FXML
    ScrollPane scrollPane;
    @FXML
    public FlowPane flowPane;
    @FXML
    Label createFileLabel;
    @FXML
    Label messageLabel = new Label();


    FileController fileController;
    ButtonController buttonController;
    TrimAlgorithm trimAlgorithm;
    AnkiConnection ankiConnection;


    //Methods
    @FXML
    private void setChooseFileButton() throws IOException {
        fileTextField.setText(fileController.chooseFile());
    }

    @FXML
    private void setCreateButton() {
        createFileLabel.setText("");
        String name = createFileTextField.getText();
        if (!name.isEmpty()) {
            fileController.userNameFile(name);
            createFileLabel.setTextFill(Color.GREEN);
            createFileLabel.setText("Successfull created!");
        } else {
            createFileLabel.setTextFill(Color.RED);
            createFileLabel.setText("Please enter a name for your file");
        }
    }

    @FXML
    private void createButtonText() {
        flowPane.getChildren().addAll(fileController.createButtonsFromFile());
    }

    @FXML
    private void doneButtonClicked() {
        translateButton.setDisable(false);
        ObservableList<Node> content = flowPane.getChildren();
        trimAlgorithm = new TrimAlgorithm(content, this);
        messageLabel.setText("");

        boolean[] result = trimAlgorithm.checkContent();
        boolean hasEndMark = result[0];
        boolean hasVocabulary = result[1];

        if (hasEndMark && hasVocabulary) {
            trimAlgorithm.getContentToTextFragments();
        } else {
            if (!hasEndMark) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("End-Mark fehlt!");
            }
            if (!hasVocabulary) {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Vokabel fehlt!");
            }
        }

    }

    @FXML
    private void translateButtonClicked() throws IOException {
        System.out.println("Translate Clicked");
        GoogleTranslateAPIConnection connection;
        HashMap<String, String> translation = new HashMap<>();
        for (Node node : flowPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                translation.put(button.getText(), null);
            }
        }
        connection = new GoogleTranslateAPIConnection(translation, this);

    }

    @FXML
    public void fillFlowPaneWithVocabulary(List<Button> buttonList) {
        flowPane.getChildren().clear();
        for (int i = 0; i < buttonList.size(); i++) {
            Button button = buttonList.get(i);
            flowPane.getChildren().add(button);

            button.setOnAction(event -> {
                Button clickedButton = (Button) event.getSource();
                int buttonIndex = buttonList.indexOf(clickedButton);

                if (buttonIndex >= 0) {
                    buttonList.remove(buttonIndex);
                    fillFlowPaneWithVocabulary(buttonList);
                }
            });
        }
    }

    @FXML
    public void fillFlowPaneTranslatedMap(HashMap<String, String> translatedMap) {
        ankiButton.setDisable(false);
        flowPane.getChildren().clear();
        for (Map.Entry<String, String> entry : translatedMap.entrySet()) {
            Button button = new Button(entry.getKey() + " " + entry.getValue());
            button.setOnAction(event -> {
                Button clickedButton = (Button) event.getSource();
                flowPane.getChildren().remove(clickedButton);

            });
            flowPane.getChildren().add(button);
        }

    }

    @FXML
    public void ankiButtonClicked() {
        ankiConnection = new AnkiConnection();
        ObservableList<String> deckList = ankiConnection.fetchDeckNames();
        ankiButton.setOnAction(new EventHandler<ActionEvent>() {
                                   public void handle(ActionEvent event) {
                                       Parent root;
                                       try {
                                           root = FXMLLoader.load(getClass().getClassLoader().getResource("selfmade.ebookConverter.view.AnkiDeckChoose"));//, resources);
                                           Stage stage = new Stage();
                                           stage.setTitle("My New Stage Title");
                                           stage.setScene(new Scene(root, 450, 450));
                                           stage.show();
                                           // Hide this current window (if this is what you want)
                                           //((Node) (event.getSource())).getScene().getWindow().hide();
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               }
        );
    }

    @FXML
    public void messageChange() {
        messageLabel.setText("");
        messageLabel.setTextFill(Color.RED);
        messageLabel.setText("Bitte markiere Titel oder Art");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fileController = new FileController();

        ChoiceBoxItems choiceBoxItems = new ChoiceBoxItems();

        rootChoiceBox.setItems(choiceBoxItems.getFirstItems());
        optionChoiceBox.setItems(choiceBoxItems.getSecondItems());
        fieldsChoiceBox.setItems(choiceBoxItems.getThirdItems());

        rootChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            ObservableList<String> updatedSecondItems = choiceBoxItems.updateSecondItems(newValue);
            optionChoiceBox.setItems(updatedSecondItems);
            fieldsChoiceBox.setItems(FXCollections.emptyObservableList());
        });

        optionChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            ObservableList<String> updatedThirdItems = choiceBoxItems.updateThirdItems(newValue);
            fieldsChoiceBox.setItems(updatedThirdItems);

        });
        fieldsChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            buttonController.setButtonChoiceBoxStatus(fieldsChoiceBox.getValue());
        });
    }

}



