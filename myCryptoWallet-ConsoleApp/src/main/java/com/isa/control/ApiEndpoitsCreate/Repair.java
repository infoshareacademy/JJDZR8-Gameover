package com.isa.control.ApiEndpoitsCreate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.control.Data;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Repair
{
    private static Map<String, String> map = new LinkedHashMap<>();

    public static void createPossibleCoinsSymbols() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.cryptowat.ch/assets"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String result = response.body();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Example example = gson.fromJson(result, Example.class);
        List<Result> results = example.getResult();
        for (Result value : results) {
            map.put(value.getSymbol(), value.getName());
        }

        String toJson = gson.toJson(map);
        Path path = Path.of("src","main","resources", "allCryptosNames.json");
        Files.write(path, toJson.getBytes());
    }
    public static void createAvailableCoinsSymbols() {
        Map<String, String> deserialized = Data.deserialize("allCryptosNames.json", map);
        int i = 1;
        int j = 1;
        for (String key : deserialized.keySet()) {
            j++;
            String url = "https://api.binance.com/api/v3/ticker/24hr?symbols=[%22" + key.toUpperCase() + "BUSD%22]";
            String request = Data.sendHttpRequest(url);
            if (!request.contains("Invalid symbol.")) {
                map.put(key.toUpperCase(), deserialized.get(key));
                System.out.println("Added:" + i++ + "records");
                Data.serializer(map, "availableCoins.json");
                i++;
            }
            System.out.println("Available records: " + i + "/" + j);
        }
    }
}
