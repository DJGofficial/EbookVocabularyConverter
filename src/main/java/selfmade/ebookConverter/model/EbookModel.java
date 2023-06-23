package selfmade.ebookConverter.model;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EbookModel {

    public static void readStart(File file) {
        try (
                FileReader fr = new FileReader(file)) {
            int content;
            while ((content = fr.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
