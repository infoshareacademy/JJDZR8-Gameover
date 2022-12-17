package com.isa;

import com.google.gson.annotations.SerializedName;

public class Coin {

    private String coinName;
    @SerializedName("symbol")
    private String coinSymbol;
    @SerializedName("lastPrice")
    private double price;
    //Enum currency;
    double coinVolume;
    String description;
    //Double changePrice;

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public enum Endpoints {
        BTC,
        ETH,
        LTC,
        DODGEBUSD
    }
}
