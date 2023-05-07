package com.isa.control;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Data {

    private static Logger LOGGER = LoggerFactory.getLogger(Data.class.getName());
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static final String pathToFile = System.getenv("FILE_PATH");

    public static void serializer(Object object, String file){
        saveToFile(gson.toJson(object), file);
    }
    public static Coin[] deserializeCoin(){
        Coin[] coins = new Gson().fromJson(loadFile("coin.json"), Coin[].class);
        for (Coin element : coins){
            element.creatNameAndShortSymbolForCoin();
        }
        LOGGER.debug("Coins array is correctly downloaded from coin.json");
        return coins;
    }
    public static Coin[] deserializeCoin(String file){
        return new Gson().fromJson(loadFile(file), Coin[].class);
    }
    public static List<Coin> deserializeCoinList(String response){
        Type foundListType = new TypeToken<ArrayList<Coin>>(){}.getType();
        List<Coin> coinsList = new Gson().fromJson(response, foundListType);
        LOGGER.debug("Coins list is correctly crated.");
        return coinsList;
    }
    public static List<String> deserializeEndpoints(){
        return new Gson().fromJson(loadFile("endpoints.json"), Endpoints.getEndpoints().getClass());
    }
    public static Map<String,String> deserialize(String file, Object object){
        return new Gson().fromJson(loadFile(file), (Type) object.getClass());
    }
    public static Wallet deserializeWallet(){
        Wallet wallet = new Gson().fromJson(loadFile("wallet.json"), Wallet.class);
        LOGGER.debug("Correctly downloaded wallet from file wallet.json.");
        return wallet;
    }
    public static Map<String,String> deserializeRequest(String response, Object object){
        return new Gson().fromJson(response, (Type) object.getClass());
    }
    public static String loadFile(String file){
        Path path = Path.of(pathToFile, file);
        System.out.println(path);
        String fromFile = null;
        try {
            fromFile = Files.readString(path);
            LOGGER.debug("Correctly downloaded from file: {}", file);
        } catch (IOException e) {
            LOGGER.error("{} loading ERROR", file);
            System.out.println("file not exist");
        }
        return fromFile;
    }
    public static void saveToFile(String data, String file){
        Path path = Path.of(pathToFile, file);
        try {
            Files.writeString(path, data);
            LOGGER.info("Correctly saved to file {}", file);
        } catch (IOException e) {
            LOGGER.error("ERROR writing to file: {}", file);
            System.out.println("path not exist " + e);
        }
    }
    public static String sendHttpRequest(String api) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api)).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info("Received a correctly response from the Binance API");
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Binance API response ERROR");
            throw new RuntimeException(e);
        }
        return response.body();
    }

    public static void updateCoinList(){
        String response = sendHttpRequest(Endpoints.buildRequest());
        saveToFile(response, "coin.json");
        System.out.println("Lista zaktualizowana pomyślnie");
        LOGGER.info("Coin List updated successfully.");
    }

    public static Map<String, String> deserializeCoinsNames() {
        return gson.fromJson(loadFile("availableCoins.json"), Endpoints.getCoinsNames().getClass());

    }
}

