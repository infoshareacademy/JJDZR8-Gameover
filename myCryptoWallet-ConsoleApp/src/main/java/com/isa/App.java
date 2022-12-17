package com.isa;

public class App {
    public static void main(String[] args) {
    //Data.sendHttpRequest(Endpoints.buildRequest(), "binanceOutput.json");
    String out = Data.sendHttpRequest("https://api.binance.com/api/v3/ticker/24hr?symbols=[%22BTCUSDT%22,%22ETHUSDT%22,%22DOGEUSDT%22]");
        System.out.println(out);
    }
}

