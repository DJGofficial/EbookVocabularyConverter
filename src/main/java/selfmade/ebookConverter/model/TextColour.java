package selfmade.ebookConverter.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextColour {
    private String name;
    private String colour;

   static ArrayList<TextColour> textColourList= new ArrayList<>();
    public TextColour(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }
    public  TextColour(){
        createTextColourList();
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public static ArrayList<TextColour> createTextColourList() {

        textColourList.add(new TextColour("Vokabel", "#FFD9D9"));
        //textColourList.add(new TextColour("Bookmarks", "#D9D9FF"));
        textColourList.add(new TextColour("Art", "#D9E5FF"));
        textColourList.add(new TextColour("Titel", "#FFFFD9"));
        textColourList.add(new TextColour("Zeichen für Ende", "#D9FFD9"));

        return textColourList;
    }

}
