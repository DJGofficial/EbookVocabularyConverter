package selfmade.ebookConverter.controller;

import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EbookController {

    private final static FileChooser fileChooser = new FileChooser();

    public static String chooseFile() throws IOException {
        fileChooser.setInitialDirectory(new File("/"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        readFile(selectedFile);
        return selectedFile.getName();//May produce 'NullPointerException
    }

    //Read content of file choosen and add it to the file
    public static void readFile(File file)  {
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

    //Not used so far???
    public static boolean createBufferedFile() {
        //Creates file when not already exist
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

    //Writes into TextFlow and writes root file
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

    /*
    //TEST ADD BUTTON
    public static ButtonBar createButtonsFromFile() {
        File toWrite = new File("filename.txt");
        ButtonBar buttonBar = new ButtonBar();

        try (BufferedReader reader = new BufferedReader(new FileReader(toWrite))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words

                for (String word : words) {
                    Button button = new Button(word);
                    button.setOnAction(event -> {
                        // Handle button click event
                        // ...
                    });
                    buttonBar.getButtons().add(button); // Add the button to the ButtonBar
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(buttonBar);
        return buttonBar;
    }

     */
    public static List<Button> createButtonsFromFile() {
        List<Button> buttons = new ArrayList<>();
        File toWrite = new File("filename.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(toWrite))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words

                for (String word : words) {
                    Button button = new Button(word);
                    button.setOnAction(event -> {
                        // Handle button click event
                        // ...
                    });
                    buttons.add(button); // Add the button to the list
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buttons;
    }

    public static boolean userNameFile(String name) {
        File createdByUser = new File(name);
        try {
            File rootFile = new File("filename.txt");
            BufferedReader reader = new BufferedReader(new FileReader(rootFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(createdByUser));

            String line;
            while ((line = reader.readLine()) != null) {
                // Zeile in Zielbenutzerdatei schreiben
                writer.write(line);
                writer.newLine();
            }

            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
