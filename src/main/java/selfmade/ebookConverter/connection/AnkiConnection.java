package selfmade.ebookConverter.connection;


import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import selfmade.ebookConverter.controller.MessageController;
import selfmade.ebookConverter.view.AnkiDeckChoose;
import selfmade.ebookConverter.view.EbookView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class AnkiConnection {
    //Info https://foosoft.net/projects/anki-connect/
    private static final String ANKI_CONNECT_URL = "http://127.0.0.1:8765";

    EbookView ebookView;// = new EbookView();
    MessageController messageController;
   AnkiDeckChoose ankiDeckChoose;
    ;

    public AnkiConnection(EbookView ebookView) {
        this.ebookView = ebookView;
    }

    /*public AnkiConnection(AnkiDeckChoose ankiDeckChoose) {
        this.ankiDeckChoose = ankiDeckChoose;
    }

     */

    public AnkiConnection() {
        this.ebookView = new EbookView();
       // this.ankiDeckChoose = new AnkiDeckChoose();
    }
/*
    public static void main(String[] args) throws IOException {
        String deckName = "ZZDeck";
        String modelName = "Basic";
        String front = "this";
        String back = "there";

        createDeck(deckName);
       // addCard(deckName, modelName, front, back);
    }

 */

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
                    deckNames.add(deckNameArray[1].replaceAll("\"", "").trim());  // Set the second entry as the first
                    for (int i = 2; i < deckNameArray.length - 1; i++) {
                        deckNames.add(deckNameArray[i].replaceAll("[\\[\\]\"]", "").trim());
                    }
                }

            }

        } catch (Exception e) {
            ebookView.setBottomLabelMessage("Bitte stelle Verbindung mit Anki über AnkiConnect her", false);
            e.printStackTrace();
        }
        System.out.println(deckNames.get(0));
        return deckNames;
    }

    public String addCard(AnkiDeckChoose ankiDecksChoose, String deckName, String modelName, String front, String back) throws IOException {
      // ArrayList<String> answerRequestList = new ArrayList<String>();
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
                    // Erfolgreiche Nachricht
                // answerRequestList.add("Erfolgreich: " + resultElement.getAsString()+" "+front);
                      return (front+": Erfolgreich: " + resultElement.getAsString());

                   // ankiDeckChoose.setTextArea("Fehlgeschlagen: " + resultElement.getAsString());
                   // System.out.println("Erfolgreich: " + resultElement.getAsString());

                } else if (errorElement != null && !errorElement.isJsonNull()) {
                    // Fehlgeschlagene Nachricht
                    String errorMessage = errorElement.getAsString();
                   //answerRequestList.add("Fehlgeschlagen: " + errorMessage+" "+front);
                    return (front+": Fehlgeschlagen: "+errorMessage);
                    // ankiDeckChoose.setTextArea("Fehlgeschlagen: " + errorMessage);
                  //  System.out.println("Fehler: " + errorMessage);

                }
                System.out.println(responseElement.getAsJsonObject());
                //?{"result":null,"error":"cannot create note because it is a duplicate"} <--If not successfull
                //? {"result":1693296648055,"error":null} <--successfull
                writer.close();
                outputStream.close();
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      /*  for(String answer: answerRequestList){
            System.out.println("AnswerRequest "+answer);

        }
        return answerRequestList;

       */
        return "Unknown Error out of scope";
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

