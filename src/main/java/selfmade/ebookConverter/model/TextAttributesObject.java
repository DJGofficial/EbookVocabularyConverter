package selfmade.ebookConverter.model;

import java.util.HashMap;


public class TextAttributesObject<T> {

    private T deckName;
    private T modelName;
    private T front;
    private T back;

    static HashMap<String, String> translatedMap;

    public TextAttributesObject(T deckName, T modelName, T front, T back) {
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

    public T getDeckName() {
        return deckName;
    }


    public T getModelName() {
        return modelName;
    }


    public T getFront() {
        return front;
    }


    public T getBack() {
        return back;
    }


}


