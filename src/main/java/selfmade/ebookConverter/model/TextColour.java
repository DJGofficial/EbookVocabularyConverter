package selfmade.ebookConverter.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextColour {
    private String name;
    private Color colour;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public static ArrayList<TextColour> createTextColourList() {
        ArrayList<TextColour> textColourList = new ArrayList<>();

        TextColour red = new TextColour();
        red.setName("Notes");
        red.setColour(Color.RED);
        textColourList.add(red);

        TextColour green = new TextColour();
        green.setName("Bookmarks");
        green.setColour(Color.GREEN);
        textColourList.add(green);

        TextColour blue = new TextColour();
        blue.setName("Author");
        blue.setColour(Color.BLUE);
        textColourList.add(blue);

        TextColour yellow = new TextColour();
        blue.setName("Title");
        blue.setColour(Color.YELLOW);
        textColourList.add(yellow);

        TextColour black = new TextColour();
        blue.setName("End");
        blue.setColour(Color.BLACK);
        textColourList.add(black);

        return textColourList;
    }

}
