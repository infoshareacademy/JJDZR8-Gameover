package com.isa;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Data {
    public static void serializer(Object object, String file){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        saveToFile(gson.toJson(object), file);
    }
    public static Coin[] deserializerCoin(){
        Gson gson = new Gson();
        return new Gson().fromJson(loadFile("Coin.json"), Coin[].class);
    }
    private static String loadFile(String file){
        Path path = Path.of("src", "main", "java", "com", "isa", "data", file);
        String fromFile = null;
        try {
            fromFile = Files.readString(path);
        } catch (IOException e) {
            System.out.println("file not exist");
        }
        return fromFile;
    }
    private static void saveToFile(String data, String file){
        Path path = Path.of("src", "main", "java", "com", "isa", "data", file);
        try {
            Files.writeString(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

