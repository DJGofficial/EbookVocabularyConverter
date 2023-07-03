package selfmade.ebookConverter.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextColour {
    private String name;
    private String colour;

   static ArrayList<TextColour> textColourList = new ArrayList<>();
    public TextColour(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }
    public  TextColour(){

    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public static ArrayList<TextColour> createTextColourList() {

        textColourList.add(new TextColour("Notes", "#FFD9D9"));
        textColourList.add(new TextColour("Bookmarks", "#D9D9FF"));
        textColourList.add(new TextColour("Author", "#D9FFD9"));
        textColourList.add(new TextColour("Title", "#FFFFD9"));
        textColourList.add(new TextColour("End", "#DDDDDD"));

        return textColourList;
    }

}
