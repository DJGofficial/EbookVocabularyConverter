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
    AnkiDeckChoose ankiDeckChoose;

    public AnkiController() {
        ankiConnection = new AnkiConnection();
        ebookView = new EbookView();
        ankiDeckChoose = new AnkiDeckChoose();
    }

    public ArrayList<String> createAndAddCards(String deckName, HashMap<String, String> translatedMap) throws IOException {
        //ankiConnection= new AnkiConnection();
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

        ArrayList<String> retValList = new ArrayList<String>();
        try {
            for (TextAttributesObject<String> textAttributes : cardList) {
                //   if (ankiDeckChoose == null) {
                System.out.println("TextAtt "+textAttributes);
               String retVal= ankiConnection.addCard(ankiDeckChoose, textAttributes.getDeckName(), textAttributes.getModelName(), textAttributes.getFront(), textAttributes.getBack());
               retValList.add(retVal);
               // }
                //  System.out.println("AnkiDeckChoose is null");
                //? maybe a message
            }
        } catch (IOException e) {
            System.out.println("Hier muss nochirgendwas hin");
            //? Hier muss noch irgendwas hin
            e.printStackTrace();
        }
        return retValList;
    }
}
