package selfmade.ebookConverter.controller;

import javafx.collections.ObservableList;
import selfmade.ebookConverter.connection.AnkiConnection;
import selfmade.ebookConverter.model.EbookViewUIManager;
import selfmade.ebookConverter.model.TextAttributesObject;
import selfmade.ebookConverter.view.AnkiDeckChoose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Die Klasse AnkiController dient als Schnittstelle zur Anki-Anwendung über AnkiConnect.
 * Sie verwaltet Operationen wie das Abrufen von Deck-Namen und das Hinzufügen von Karten zu einem bestimmten Deck.
 */
public class AnkiController {

    private AnkiConnection ankiConnection;
    private AnkiDeckChoose ankiDeckChoose;
    private EbookViewUIManager uiManager;


    public AnkiController() {
        this.uiManager = new EbookViewUIManager();
        this.ankiConnection = new AnkiConnection(uiManager);
        this.ankiDeckChoose = new AnkiDeckChoose();
    }

    public void handleAnkiDeck() {
        ObservableList<String> deckList = ankiConnection.fetchDeckNames();
        if (deckList != null && !deckList.isEmpty()) {
            ankiDeckChoose.callWindowAddDeckList(deckList);
        } else {
            uiManager.setBottomMessage(false, "Bitte stelle Verbindung mit Anki über AnkiConnect her");
        }
    }

    public ArrayList<String> createAndAddCards(String deckName, HashMap<String, String> translatedMap) throws IOException {
        String modelName = "Basic (and reversed card)";

        for (Map.Entry<String, String> entry : translatedMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        List<TextAttributesObject<String>> cardList = new ArrayList<>();

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

    public EbookViewUIManager getUiManager() {
        return uiManager;
    }
}
