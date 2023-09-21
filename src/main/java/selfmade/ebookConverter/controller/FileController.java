package selfmade.ebookConverter.controller;

import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController {

    private final static FileChooser fileChooser = new FileChooser();

    ButtonController buttonController = new ButtonController();

    public static String chooseFile() throws IOException {
        fileChooser.setInitialDirectory(new File("/"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        readFile(selectedFile);
        return selectedFile.getName();
    }

    public static void readFile(File file) {
        try (
                FileWriter myWriter = new FileWriter("filename.txt");
                FileReader fr = new FileReader(file)) {
            int content;
            while ((content = fr.read()) != -1) {
                myWriter.write((char) content);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean createBufferedFile() {
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                return true;
            } else {
                System.out.println("File already exists.");
                return true;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }


    public static Text writeFile() {
        File toWrite = new File("filename.txt");
        String textFromFile = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(toWrite))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            textFromFile = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Text retVal = new Text(textFromFile);
        return retVal;
    }


    public List<ToggleButton> createButtonsFromFile() {
        List<ToggleButton> buttons = new ArrayList<>();
        File toWrite = new File("filename.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(toWrite))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    ToggleButton button = new ToggleButton(word);
                    button.setStyle(buttonController.createStyledButton());
                    button.setOnAction(event -> {
                        setButtonAction(button);
                    });
                    buttons.add(button);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buttons;
    }

    public void setButtonAction(ToggleButton button) {
        if (button.isSelected() == true) {
            button.setStyle("-fx-background-color: " + buttonController.getButtonColour());
        } else {
            button.setStyle("");
        }
    }

    public static boolean userNameFile(String name) {
        File createdByUser = new File(name);
        try {
            File rootFile = new File("filename.txt");
            BufferedReader reader = new BufferedReader(new FileReader(rootFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(createdByUser));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    public static String chooseFile() throws IOException {
        fileChooser.setInitialDirectory(new File("/"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {  //Why??? No body here
        }
        readFile(selectedFile);
        return selectedFile.getName();//May produce 'NullPointerException
    }
     public static void readFile(File file) throws IOException {
        FileWriter myWriter = new FileWriter("filename.txt");
        try (
                FileReader fr = new FileReader(file)) {
            int content;
            while ((content = fr.read()) != -1) {

                myWriter.write((char) content);
                System.out.print((char) content);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void setButtonColour(String value) {
        for (TextColour tc : textColour.textColourList) {
            if (tc.getName().equals(value)) {
                this.buttonChoiceBoxStatus = tc.getColour();

            }
        }
    }
     */
}
