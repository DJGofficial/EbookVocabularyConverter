package selfmade.ebookConverter.model;


import javafx.stage.FileChooser;
import javafx.stage.Stage;
import selfmade.ebookConverter.view.EbookView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EbookModel {

    private static FileChooser fileChooser = new FileChooser();

    public static String chooseFile(File file) throws IOException {
        fileChooser.setInitialDirectory(new File("/"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
        }
        readFile(selectedFile);
        return selectedFile.getName();
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

    public static File writeFile() {
        File toWrite = new File("filename.txt");
        return toWrite;
    }

    public static boolean userNameFile(String name) {
        File namedFile = new File(name);
        return true;
    }
}
