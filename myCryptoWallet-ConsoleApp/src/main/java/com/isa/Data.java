package com.isa;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Data {

    public static void serializer(Object object, String file){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        saveToFile(gson.toJson(object), file);
    }
    public static Coin[] deserializeCoin(){
        Gson gson = new Gson();
        return new Gson().fromJson(loadFile("coin.json"), Coin[].class);
    }
    public static Coin[] deserializeCoin(String file){
        Gson gson = new Gson();
        return new Gson().fromJson(loadFile(file), Coin[].class);
    }
    public static List<String> deserializeEndpoints(){
        Gson gson = new Gson();
        return new Gson().fromJson(loadFile("endpoints.json"), Endpoints.getEndpoints().getClass());
    }
    public static String loadFile(String file){
        Path path = Path.of("src", "main", "java", "com", "isa", "data", file);
        String fromFile = null;
        try {
            fromFile = Files.readString(path);
        } catch (IOException e) {
            System.out.println("file not exist");
        }
        return fromFile;
    }
    public static void saveToFile(String data, String file){
        Path path = Path.of("src", "main", "java", "com", "isa", "data", file);
        try {
            Files.writeString(path, data);
        } catch (IOException e) {
            System.out.println("path not exist " + e);
        }
    }
    public static String sendHttpRequest(String api) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api)).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);  //FIXME
        }
        return response.body();
    }

    public static void updateCoinList(){
        String response = sendHttpRequest(Endpoints.buildRequest());
        saveToFile(response, "coin.json");
        System.out.println("Lista zaktualizowana pomyślnie");
    }
}

