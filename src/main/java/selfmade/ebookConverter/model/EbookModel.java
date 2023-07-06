package selfmade.ebookConverter.model;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class EbookModel {

    private static FileChooser fileChooser = new FileChooser();

    private static String buttonChoiceBoxStatus;

    static TextColour textColour = new TextColour();

    public EbookModel() {
    }

    public static void setButtonChoiceBoxStatus(String value) {
        buttonChoiceBoxStatus = value;
    }

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

    public void setButtonColour(String value) {
        for (TextColour tc : textColour.textColourList) {
            if (tc.getName().equals(value)) {
                this.buttonChoiceBoxStatus = tc.getColour();

            }
        }
    }

    public String getButtonColour() {
        System.out.println(buttonChoiceBoxStatus);
        if (buttonChoiceBoxStatus != null) {
            for (TextColour tC : TextColour.createTextColourList()) {
                if (buttonChoiceBoxStatus.equals(tC.getName())) {
                    return tC.getColour();
                }
            }
        }
        return null;
    }
}

