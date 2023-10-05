package selfmade.ebookConverter.controller;

import javafx.collections.ObservableList;
import selfmade.ebookConverter.connection.AnkiConnection;
import selfmade.ebookConverter.model.EbookViewUIManager;
import selfmade.ebookConverter.model.TextAttributesObject;
import selfmade.ebookConverter.view.AnkiDeckChoose;
import selfmade.ebookConverter.view.EbookView;

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

    AnkiConnection ankiConnection;
    EbookView ebookView;
    AnkiDeckChoose ankiDeckChoose;
    MessageController messageController;

    /**
     * Standardkonstruktor, der AnkiConnection, EbookView und andere notwendige Komponenten initialisiert.
     */
    public AnkiController() {
        ankiConnection = new AnkiConnection();
        ebookView = new EbookView();
        ankiDeckChoose = new AnkiDeckChoose();
        messageController= new MessageController();
    }
    /**
     * Verwaltet die Auswahl eines Anki-Decks, indem verfügbare Deck-Namen abgerufen und angezeigt werden.
     *
     * @param ebookView  Die Instanz von EbookView, die die UI-Elemente enthält.
     * @param uiManager  Die Instanz von UIManager, die UI-Operationen verwaltet.
     */
    public void handleAnkiDeck(EbookView ebookView, EbookViewUIManager uiManager) {
        ObservableList<String> deckList = ankiConnection.fetchDeckNames();
        if (deckList != null && !deckList.isEmpty()) {
            ankiDeckChoose.callWindowAddDeckList(deckList);
        } else {
            uiManager.setBottomMessage(false,"Bitte stelle Verbindung mit Anki über AnkiConnect her");
        }
    }
    /**
     * Erstellt Anki-Karten basierend auf dem übersetzten Text und fügt sie einem angegebenen Deck hinzu.
     *
     * @param deckName      Der Name des Decks, in dem die Karten hinzugefügt werden sollen.
     * @param translatedMap Eine Map, die den Originaltext als Schlüssel und den übersetzten Text als Wert enthält.
     * @return              Eine Liste von Meldungen, die den Erfolg oder Misserfolg jeder Kartenhinzufügung anzeigen.
     * @throws IOException  Falls es ein Netzwerkproblem bei der Kommunikation mit AnkiConnect gibt.
     */
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
