package selfmade.ebookConverter.model;

import java.util.HashMap;

public class TextAttributesObject<T> {

    private String deckName;
    private String modelName;
    private String front;
    private String back;

    static HashMap<String, String> translatedMap;

    public TextAttributesObject(String deckName, String modelName, String front, String back) {
        this.deckName = deckName;
        this.modelName = modelName;
        this.front = front;
        this.back = back;

    }

    public TextAttributesObject() {
    }

    public static HashMap<String, String> getTranslatedMap() {
        return translatedMap;
    }

    public static void setTranslatedMap(HashMap<String, String> transMap) {
        translatedMap = transMap;
    }

    public String getDeckName() {
        return deckName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }


}


