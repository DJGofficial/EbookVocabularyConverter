package selfmade.ebookConverter.connection;


import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import selfmade.ebookConverter.view.EbookView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class AnkiConnection {
    //Info https://foosoft.net/projects/anki-connect/
    private static final String ANKI_CONNECT_URL = "http://127.0.0.1:8765";

    EbookView ebookView= new EbookView();

    public AnkiConnection(EbookView ebookView) {
        this.ebookView = ebookView;
    }

    public static void main(String[] args) throws IOException {
        String deckName = "ZZDeck";
        String modelName = "Basic";
        String front = "this";
        String back = "there";

        createDeck(deckName);
        addCard(deckName, modelName, front, back);
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
                jsonString = jsonString.substring(1, jsonString.length() - 1); // Remove enclosing []
                String[] deckNameArray = jsonString.split(",");
                for (String deckName : deckNameArray) {
                    deckNames.add(deckName.replaceAll("\"", ""));
                }
            }

        } catch (Exception e) {
            System.out.println("CachException arrived");
            ebookView.setBottomLabelMessage("Please connect to Anki, see ... for further help");
            e.printStackTrace();
        }
        System.out.println(deckNames);
        return deckNames;
    }

    public static void addCard(String deckName, String modelName, String front, String back) throws IOException {
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
            notes.addProperty("modelName", "Basic");

            JsonObject fields = new JsonObject();
            fields.add("Front", new JsonPrimitive(front));
            fields.add("Back", new JsonPrimitive(back));

            notes.add("fields", fields);
            params.add("note", notes);
            request.add("params", params);

            // JsonObject note = new JsonObject();
            // note.addProperty("deckName", deckName);
            // note.addProperty("modelName", modelName);

            //  JsonObject fields = new JsonObject();
            // fields.add("Front", new JsonPrimitive(front));
            // fields.add("Back", new JsonPrimitive(back));
            // note.add("fields", fields);


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
                // if (responseElement.isJsonObject()) {
                System.out.println(responseElement.getAsJsonObject());

                writer.close();
                outputStream.close();
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDeck(String deckName) throws IOException {
        try {
            URL url = new URL(ANKI_CONNECT_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);


            JsonObject request = new JsonObject();
            request.addProperty("action", "createDeck");
            request.addProperty("version", 6);

            JsonObject params = new JsonObject();
            params.addProperty("deck", deckName);

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
                // if (responseElement.isJsonObject()) {
                System.out.println(responseElement.getAsJsonObject());
                //   System.out.println("Karte wurde zur Anki-Kollektion hinzugefügt." + responseCode);
                //   } else {
                //    System.err.println("Fehler beim Hinzufügen der Karte zur Anki-Kollektion. Antwortcode: " + responseCode);
                // }
            }

            writer.close();
            outputStream.close();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

