package selfmade.ebookConverter.controller;

import selfmade.ebookConverter.connection.AnkiConnection;
import selfmade.ebookConverter.model.TextAttributesObject;
import selfmade.ebookConverter.view.AnkiDeckChoose;
import selfmade.ebookConverter.view.EbookView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnkiController {

    AnkiConnection ankiConnection;//= new AnkiConnection();
    EbookView ebookView;
    public AnkiController() {
        ebookView= new EbookView();
    }

    public void createAndAddCards(AnkiConnection ankiConnection, String deckName, HashMap<String, String> translatedMap) {
        ankiConnection= new AnkiConnection();
        for (Map.Entry<String, String> entry : translatedMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        List<TextAttributesObject<String>> cardList = new ArrayList<>();

        String modelName = "Basic";

        for (Map.Entry<String, String> entry : translatedMap.entrySet()) {
            String front = entry.getKey();
            String back = entry.getValue();

            TextAttributesObject<String> textAttributes = new TextAttributesObject<>(deckName, modelName, front, back, false, false);
            cardList.add(textAttributes);
        }

        try {
            for (TextAttributesObject<String> textAttributes : cardList) {
                ankiConnection.addCard(textAttributes.getDeckName(), textAttributes.getModelName(), textAttributes.getFront(), textAttributes.getBack());
            }
        } catch (IOException e) {
            System.out.println("Hier muss nochirgendwas hin");
            //? Hier muss noch irgendwas hin
            e.printStackTrace();
        }
    }
}
