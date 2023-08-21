package selfmade.ebookConverter.connection;

//import com.google.auth.oauth2.GoogleCredentials;

import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.v3.*;

import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


import selfmade.ebookConverter.view.EbookView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GoogleTranslateAPIConnection {
    EbookView ebookView;
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
        ebookView.fillFlowPaneTranslatedMap(testMap);//translatedMap);
    }
}

/*
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        translationServiceClient = TranslationServiceClient.create(TranslationServiceSettings.newBuilder().setCredentialsProvider(() -> credentials).build());

        for (Object entry : vocList.entrySet()) {
            TranslateTextRequest translateTextRequest = TranslateTextRequest.newBuilder().
                    //  translateText(entry.toString(),"en");
                            sourceLanguageCode("en").
                    targetLanguageCode(targetLanguage).
                    text(entry.toString()).build();
            TranslateTextResponse translateTextResponse = translationServiceClient.translateText(translateTextRequest);
            String translatedText = translateTextResponse.getTranslatedText();
            System.out.println(entry.toString() + translatedText);

            // System.out.println("Key: " + entry.toString() + ", Value: " + entry.toString());
        }
    }
}
/*
    public void close() {
        if (translationServiceClient != null) {
            translationServiceClient.close();
        }
    }

    public void translateText(String text, String targetLanguage) throws IOException {
        TranslationServiceClient request = TranslateTextRequest.newBuilder()
                .setContents(text)
                .setTargetLanguageCode(targetLanguage)
                .build();

        TranslateTextResponse response = translateTextClient.translateText(request);

        for (Translation translation : response.getTranslationsList()) {
            System.out.println("Original Text: " + text);
            System.out.println("Translated Text: " + translation.getTranslatedText());
        }
    }
/*
    private void translateText(String text) throws IOException {
        String projectId = "ebookreaderapi";
        String sourceLanguage = "en"; // Assuming source language is English

        // Create translation request
        TranslateTextRequest request =
                TranslateTextRequest.newBuilder()
                        .setParent(LocationName.of(projectId, "global").toString())                        .setMimeType("text/plain")
                        .setSourceLanguageCode(sourceLanguage)
                        .setTargetLanguageCode(targetLanguage)
                        .addContents(text)
                        .build();

        // Perform translation
        TranslateTextResponse response = translationServiceClient.translateText(request);

        // Print the translated text
        for (Translation translation : response.getTranslationsList()) {
            System.out.println("Original Text: " + text);
            System.out.println("Translated Text: " + translation.getTranslatedText());
        }
    }

    // Close the TranslateTextClient when done
    public void close() {
        if (translationServiceClient != null) {
            translationServiceClient.close();
        }
    }
}

        private void translateText (String text) throws IOException {//String voc) {
            String projectId = "ebookreaderapi";
            String targetLanguage = "es";
            String text = "Hello world!";
            translateText(projectId, targetLanguage, text);


        ranslation translation = translate.translate(voc, Translate.TranslateOption.targetLanguage(targetLanguage));

        System.out.printf("Original: %s%n", voc);
        System.out.printf("Übersetzung (%s): %s%n", targetLanguage, translation.getTranslatedText());




        }
        private void translateText (String projectId, String targetLanguage, String text) throws IOException {

            try (TranslationServiceClient client = TranslationServiceClient.create()) {
                LocationName parent = LocationName.of(projectId, "global");

                TranslateTextRequest request =
                        TranslateTextRequest.newBuilder()
                                .setParent(parent.toString())
                                .setMimeType("text/plain")
                                .setTargetLanguageCode(targetLanguage)
                                .addContents(text)
                                .build();

                TranslateTextResponse response = client.translateText(request);

                // Display the translation for each input text provided
                for (Translation translation : response.getTranslationsList()) {
                    System.out.printf("Translated text: %s\n", translation.getTranslatedText());
                }
            }
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







