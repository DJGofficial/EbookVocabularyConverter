package selfmade.ebookConverter.connection;

//import com.google.auth.oauth2.GoogleCredentials;

import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.v3.*;

import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


import selfmade.ebookConverter.model.TextAttributesObject;
import selfmade.ebookConverter.view.EbookView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GoogleTranslateAPIConnection {
    EbookView ebookView;
    TextAttributesObject textAttributesObject= new TextAttributesObject<>();
    String targetLanguage = "de";

    public GoogleTranslateAPIConnection(HashMap<?, ?> vocList, EbookView ebookView) throws IOException {
        this.ebookView = ebookView;
        translateAndReturnHashMap(vocList);

    }

    public void translateAndReturnHashMap(HashMap<?, ?> vocList) {
        HashMap<String, String> translatedMap = new HashMap<>();
/*
        for (Map.Entry<?, ?> entry : vocList.entrySet()) {
            try {
                Translate translate = TranslateOptions.getDefaultInstance().getService();
                Translation translation = translate.translate(String.valueOf(entry.getKey()),
                        Translate.TranslateOption.sourceLanguage("en"),
                        Translate.TranslateOption.targetLanguage("de"));

                String originalText = String.valueOf(entry.getKey());
                String translatedText = (translation != null && translation.getTranslatedText() != null)
                        ? translation.getTranslatedText()
                        : "Translation failed for: " + entry.getValue();

                translatedMap.put(originalText, translatedText);
            } catch (Exception e) {
                System.err.println("Error translating: " + e.getMessage());
            }
        }
        for (Map.Entry<String, String> entry : translatedMap.entrySet()) {
            System.out.println("Original Text: " + entry.getKey());
            System.out.println("Translated Text: " + entry.getValue());
            System.out.println();
        }

 */
        HashMap<String, String> testMap= new HashMap<>();
        testMap.put("deutsch1","englisch1");
        testMap.put("deutsch2","englisch2");
        testMap.put("deutsch3","englisch3");
        ebookView.fillFlowPaneTranslatedMap(testMap);
        textAttributesObject.setTranslatedMap(testMap);

    }
}








