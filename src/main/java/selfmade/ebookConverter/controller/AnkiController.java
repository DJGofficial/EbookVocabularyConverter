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

    AnkiConnection ankiConnection;
    EbookView ebookView;
    AnkiDeckChoose ankiDeckChoose;

    public AnkiController() {
        ankiConnection = new AnkiConnection();
        ebookView = new EbookView();
        ankiDeckChoose = new AnkiDeckChoose();
    }

    public ArrayList<String> createAndAddCards(String deckName, HashMap<String, String> translatedMap) throws IOException {

        for (Map.Entry<String, String> entry : translatedMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        List<TextAttributesObject<String>> cardList = new ArrayList<>();

        String modelName = "Basic (and reversed card)";

        for (Map.Entry<String, String> entry : translatedMap.entrySet()) {
            String front = entry.getKey();
            String back = entry.getValue();

            TextAttributesObject<String> textAttributes = new TextAttributesObject<>(deckName, modelName, front, back);
            cardList.add(textAttributes);
        }

        ArrayList<String> retValList = new ArrayList<>();
        try {
            for (TextAttributesObject<String> textAttributes : cardList) {
                String retVal = ankiConnection.addCard(textAttributes.getDeckName(), textAttributes.getModelName(), textAttributes.getFront(), textAttributes.getBack());
                retValList.add(retVal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retValList;
    }
}
