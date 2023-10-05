package selfmade.ebookConverter.model;

import java.util.ArrayList;

/**
 * Diese Klasse repräsentiert eine Textfarbe mit einem zugehörigen Namen.
 */
public class TextColour {
    private String name;
    private String colour;
    // Statische Liste, die verschiedene Instanzen von TextColour enthält
    static ArrayList<TextColour> textColourList = new ArrayList<>();

    /**
     * Konstruktor, der eine Textfarbe mit einem Namen und einer Farbe initialisiert.
     *
     * @param name   Der Name der Textfarbe.
     * @param colour Die zugehörige Farbe im Hex-Format.
     */
    public TextColour(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    /**
     * Standardkonstruktor, der die Liste der Textfarben initialisiert.
     */
    public TextColour() {
        createTextColourList();
    }

    /**
     * @return Gibt den Namen der Textfarbe zurück.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Gibt die Farbe der Textfarbe im Hex-Format zurück.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Erstellt und gibt eine Liste von Textfarben zurück.
     *
     * @return Eine Liste von Textfarben.
     */
    public static ArrayList<TextColour> createTextColourList() {

        textColourList.add(new TextColour("Vokabel", "#FFD9D9"));
        textColourList.add(new TextColour("Art (optional)", "#D9E5FF"));
        textColourList.add(new TextColour("Titel (optional)", "#FFFFD9"));
        textColourList.add(new TextColour("Zeichen für Ende", "#D9FFD9"));
        return textColourList;
    }

}
