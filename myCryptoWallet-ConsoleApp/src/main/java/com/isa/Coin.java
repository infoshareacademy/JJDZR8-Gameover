package com.isa;

public class Coin {
    String coinName;
    String coinSymbol;
    Double price;
    Enum currency;
    Double coinVolumen;
    String description;
    Double changePrice;

    public Coin(String coinName, String coinSymbol, Double price) {
        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
        this.price = price;
    }

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

    public Enum getCurrency() {
        return currency;
    }

    public void setCurrency(Enum currency) {
        this.currency = currency;
    }

    public Double getCoinVolumen() {
        return coinVolumen;
    }

    public void setCoinVolumen(Double coinVolumen) {
        this.coinVolumen = coinVolumen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(Double changePrice) {
        this.changePrice = changePrice;
    }
}
