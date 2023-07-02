package selfmade.ebookConverter.model;


import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class EbookModel {

    private static FileChooser fileChooser = new FileChooser();

    static Color buttonChoiceBoxStatus = null;

    static TextColour textColour = new TextColour();


    public static String chooseFile() throws IOException {
        fileChooser.setInitialDirectory(new File("/"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {  //Why??? No body here
        }
        readFile(selectedFile);
        return selectedFile.getName();//May produce 'NullPointerException
    }

    //Read content of file choosen and add it to the file
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

    public static void setButtonColour(String value) {
        for (TextColour tc : textColour.textColourList) {
            if (tc.getName().equals(value)) {
                buttonChoiceBoxStatus = tc.getColour();

            }
        }
    }

    public static Color getButtonColour() {
        return buttonChoiceBoxStatus;
    }
}
