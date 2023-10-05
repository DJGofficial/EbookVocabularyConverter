package selfmade.ebookConverter.model;

import java.util.HashMap;

/**
 * Diese Klasse stellt ein Textattributsobjekt dar, das verschiedene Attribute einer Karteikarte enthält.
 *
 * @param <T> Generischer Typ.
 */
public class TextAttributesObject<T> {

    private String deckName;
    private String modelName;
    private String front;
    private String back;

    static HashMap<String, String> translatedMap;

    /**
     * Konstruktor zum Initialisieren aller Attribute einer Karteikarte.
     *
     * @param deckName  Der Name des Stapels (Deck), zu dem die Karteikarte gehört.
     * @param modelName Der Name des Modells (Vorlage) der Karteikarte.
     * @param front     Der Text auf der Vorderseite der Karteikarte.
     * @param back      Der Text auf der Rückseite der Karteikarte.
     */
    public TextAttributesObject(String deckName, String modelName, String front, String back) {
        this.deckName = deckName;
        this.modelName = modelName;
        this.front = front;
        this.back = back;

    }

    /**
     * Standardkonstruktor.
     */
    public TextAttributesObject() {
    }

    /**
     * @return Gibt die Übersetzungen als HashMap zurück.
     */
    public static HashMap<String, String> getTranslatedMap() {
        return translatedMap;
    }

    /**
     * Setzt die Übersetzungen für diese Klasse.
     *
     * @param transMap Die Übersetzungen als HashMap.
     */
    public static void setTranslatedMap(HashMap<String, String> transMap) {
        translatedMap = transMap;
    }

    /**
     * @return Gibt den Namen des Stapels zurück.
     */
    public String getDeckName() {
        return deckName;
    }

    /**
     * @return Gibt den Namen des Modells zurück.
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @return Gibt den Text der Vorderseite zurück.
     */
    public String getFront() {
        return front;
    }

    /**
     * @return Gibt den Text der Rückseite zurück.
     */
    public String getBack() {
        return back;
    }


}


