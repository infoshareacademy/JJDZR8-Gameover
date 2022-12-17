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

public class Data {
    public static void serializer(Object object, String file){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        saveToFile(gson.toJson(object), file);
    }
    public static Coin[] deserializeCoin(){
        Gson gson = new Gson();
        return new Gson().fromJson(loadFile("Coin.json"), Coin[].class);
    }
    public static Coin[] deserializeCoin(String file){
        Gson gson = new Gson();
        return new Gson().fromJson(loadFile(file), Coin[].class);
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
            throw new RuntimeException(e);
        }
    }
    public static String sendHttpRequest(String api) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
        return client.toString();
    }
    public static void sendHttpRequest(String api, String file) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
        String out = client.toString();
        Data.saveToFile(out, file);
    }
}

