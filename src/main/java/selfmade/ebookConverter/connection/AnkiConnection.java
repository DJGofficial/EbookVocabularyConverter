package selfmade.ebookConverter.connection;


import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import selfmade.ebookConverter.controller.EbookViewUIManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class AnkiConnection {
    //Info https://foosoft.net/projects/anki-connect/
    private static final String ANKI_CONNECT_URL = "http://127.0.0.1:8765";

    private EbookViewUIManager uiManager;

    public AnkiConnection(EbookViewUIManager uiManager) {
        this.uiManager = uiManager;
    }

    public ObservableList<String> fetchDeckNames() {
        ObservableList<String> deckNames = FXCollections.observableArrayList();

        try {
            URL url = new URL(ANKI_CONNECT_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            String request = "{\"action\": \"deckNames\", \"version\": 6}";
            os.write(request.getBytes());
            os.flush();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonString = response.toString();
                String[] deckNameArray = jsonString.split(",");

                if (deckNameArray.length >= 2) {
                    deckNames.add(deckNameArray[1].replaceAll("\"", "").trim());
                    for (int i = 2; i < deckNameArray.length - 1; i++) {
                        deckNames.add(deckNameArray[i].replaceAll("[\\[\\]\"]", "").trim());
                    }
                }

            }

        } catch (Exception e) {
            uiManager.setBottomMessage(false,"Bitte stelle Verbindung mit Anki über AnkiConnect her");
            e.printStackTrace();
        }
        System.out.println(deckNames);
        return deckNames;
    }

    public String addCard(String deckName, String modelName, String front, String back) throws IOException {

        try {
            URL url = new URL(ANKI_CONNECT_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);

            JsonObject request = new JsonObject();
            request.addProperty("action", "addNote");
            request.addProperty("version", 6);

            JsonObject params = new JsonObject();

            JsonObject notes = new JsonObject();
            notes.addProperty("deckName", deckName);
            notes.addProperty("modelName", modelName);

            JsonObject fields = new JsonObject();
            fields.add("Front", new JsonPrimitive(front));
            fields.add("Back", new JsonPrimitive(back));

            notes.add("fields", fields);
            params.add("note", notes);
            request.add("params", params);

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
            writer.write(request.toString());
            writer.flush();


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();

                JsonParser parser = new JsonParser();
                JsonElement responseElement = parser.parse(responseBuilder.toString());
                JsonObject responseObject = responseElement.getAsJsonObject();
                JsonElement resultElement = responseObject.get("result");
                JsonElement errorElement = responseObject.get("error");

                if (resultElement != null && !resultElement.isJsonNull()) {
                    return (front + ": Erfolgreich: " + resultElement.getAsString());
                } else if (errorElement != null && !errorElement.isJsonNull()) {
                    String errorMessage = errorElement.getAsString();
                    return (front + ": Fehlgeschlagen: " + errorMessage);

                }

                writer.close();
                outputStream.close();
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return  "Unbekannter Fehler außerhalb des Geltungsbereiches";
    }

}

