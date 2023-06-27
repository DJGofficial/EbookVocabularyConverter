package selfmade.ebookConverter.anki;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class AnkiController {

    private static final String ANKI_CONNECT_URL = "http://127.0.0.1:8765";

    public static void main(String[] args) {
        String deckName = "TestDeck";
        String modelName = "Basic";
        String front = "this";
        String back = "there";


        addCard(deckName, modelName, front, back);
    }

    public static void addCard(String deckName, String modelName, String front, String back) {

        try {
            // Erstelle eine Verbindung zur AnkiConnect API
            URL url = new URL(ANKI_CONNECT_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);

            // Erstelle das JSON-Objekt für die AnkiConnect-Anfrage
            JsonObject request = new JsonObject();
            request.addProperty("action", "guiAddCards");
            request.addProperty("version", 6);

            JsonObject params = new JsonObject();

            // Erstelle das JSON-Objekt für das Kartenmodell
            JsonObject note = new JsonObject();
            note.addProperty("deckName", deckName);
            note.addProperty("modelName", modelName);

            JsonObject fields = new JsonObject();
            fields.add("Front", new JsonPrimitive(front));
            fields.add("Back", new JsonPrimitive(back));
            note.add("fields", fields);

            // Füge das Kartenmodell zur Anfrage hinzu
            request.add("params", note);

            // Sende die Anfrage an AnkiConnect
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
            writer.write(request.toString());
            writer.flush();

            // Lese die Antwort von AnkiConnect
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Die Anfrage war erfolgreich
                // Du kannst hier zusätzlichen Code hinzufügen, um die Antwort zu verarbeiten
                System.out.println("Karte wurde zur Anki-Kollektion hinzugefügt.");
            } else {
                // Die Anfrage ist fehlgeschlagen
                System.err.println("Fehler beim Hinzufügen der Karte zur Anki-Kollektion. Antwortcode: " + responseCode);
            }

            // Schließe die Verbindung
            writer.close();
            outputStream.close();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
