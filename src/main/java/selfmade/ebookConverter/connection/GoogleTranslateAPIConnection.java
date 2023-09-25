package selfmade.ebookConverter.connection;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import selfmade.ebookConverter.model.TextAttributesObject;
import selfmade.ebookConverter.view.EbookView;

import java.util.HashMap;
import java.util.Map;

public class GoogleTranslateAPIConnection {
    private TextAttributesObject textAttributesObject = new TextAttributesObject<>();
    private final String apiKey = "AIzaSyB0VYtdAisUlT0Q_JEl4K0uliB0Kiu14HQ";

    public void translateAndReturnHashMap(EbookView ebookView, HashMap<?, ?> vocList) {

        HashMap<String, String> translatedMap = new HashMap<>();

        for (Map.Entry<?, ?> entry : vocList.entrySet()) {
            try {
                //Translate translate = TranslateOptions.getDefaultInstance().getService();
                TranslateOptions options = TranslateOptions.newBuilder().setApiKey(apiKey).build();
                Translate translate = options.getService();
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

        ebookView.fillFlowPaneTranslatedMap(translatedMap);
        textAttributesObject.setTranslatedMap(translatedMap);

    }
}








