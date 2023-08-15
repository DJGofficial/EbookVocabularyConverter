package selfmade.ebookConverter.connection;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.Service;
import com.google.cloud.ServiceOptions;
import selfmade.ebookConverter.view.EbookView;

import java.util.HashMap;
import java.util.Map;

public class GoogleTranslateAPIConnection {
    EbookView ebookView;
    String targetLanguage = "de";
    Translate translate = TranslateOptions.getDefaultInstance().getService();

    public GoogleTranslateAPIConnection(HashMap vocList) {
        ebookView = new EbookView();
        for (Object entry : vocList.entrySet()) {
            translateText(entry.toString());
            // System.out.println("Key: " + entry.toString() + ", Value: " + entry.toString());
        }
    }

    private void translateText(String voc) {
        Translation translation = translate.translate(voc, Translate.TranslateOption.targetLanguage(targetLanguage));

        System.out.printf("Original: %s%n", voc);
        System.out.printf("Übersetzung (%s): %s%n", targetLanguage, translation.getTranslatedText());


    }

}
/*
     Translation translation =
                 translate.translate(
                         "Hola Mundo!",
                         Translate.TranslateOption.sourceLanguage("es"),
                         Translate.TranslateOption.targetLanguage("de"),
                         // Use "base" for standard edition, "nmt" for the premium model.
                         Translate.TranslateOption.model("nmt"));

         System.out.printf("TranslatedText:\nText: %s\n", translation.getTranslatedText());


 */







