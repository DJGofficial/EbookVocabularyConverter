package selfmade.ebookConverter.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextColour {
    private String name;
    private Color colour;

   static ArrayList<TextColour> textColourList = new ArrayList<>();
    public TextColour(String name, Color colour) {
        this.name = name;
        this.colour = colour;
    }
    public  TextColour(){

    }

    public String getName() {
        return name;
    }

    public Color getColour() {
        return colour;
    }

    public static ArrayList<TextColour> createTextColourList() {

        textColourList.add(new TextColour("Notes", Color.RED));
        textColourList.add(new TextColour("Bookmarks", Color.GREEN));
        textColourList.add(new TextColour("Author", Color.BLUE));
        textColourList.add(new TextColour("Title", Color.YELLOW));
        textColourList.add(new TextColour("End", Color.BLACK));

        return textColourList;
    }

}
