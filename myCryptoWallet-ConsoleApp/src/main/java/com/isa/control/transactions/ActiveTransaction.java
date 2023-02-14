package com.isa.control.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.control.Coin;
import com.isa.control.Data;
import com.isa.control.Endpoints;

import java.util.Objects;

public class ActiveTransaction extends Transactions implements Transaction{

    private String openTransactionDate;
    private double openPrice;
    private double currentPrice;

    public ActiveTransaction(){}

    public ActiveTransaction(Coin coin, double volume) {
        super(coin, true, volume, Transaction.newDate.getTime());
        this.openPrice = Double.parseDouble(coin.getLastPrice());
        this.currentPrice = Double.parseDouble(coin.getLastPrice());
        this.openTransactionDate = establishOpenTransactionDate();
    }

    public ActiveTransaction(ActiveTransaction activeTransaction, double volume){
        super(activeTransaction.getCoin(), true, volume, Transaction.newDate.getTime());
        this.openPrice = activeTransaction.getOpenPrice();
        this.openTransactionDate = activeTransaction.getOpenTransactionDate();
        this.currentPrice = activeTransaction.getCurrentPrice();
    }

    @Override
    public double countProfit() {
        return (currentPrice - openPrice) * getVolume();
    }
    @Override
    public void  refreshPrice(){
       if(checkEndpointsName()){
           String request = Endpoints.buildRequest(getCoin().getShortSymbol());
           String response = Data.sendHttpRequest(request);
           if(response.contains("\"code\":-1100")) {
               System.out.println("cena nie została zaktualizowana");
           }else {
               Gson gson = new GsonBuilder().setPrettyPrinting().create();
               Coin[] coin = gson.fromJson(response, Coin[].class);
                this.currentPrice = Double.parseDouble(coin[0].getLastPrice());
           }
       }
    }

    @Override
    public void printDetails() {
        System.out.println("id Transakcji" + getIdTransaction());
        System.out.println(getCoin().getName() + " " + getCoin().getShortSymbol() +
                "cena zakupu " + openPrice + "cena aktualna " + currentPrice + "ilość: " + getVolume());
        System.out.println("Zysk/Strata: " + countProfit());

    }
    public double countTransactionCost(){
        return openPrice * getVolume();
    }

    public String getOpenTransactionDate() {
        return openTransactionDate;
    }

    @Override
    public String establishOpenTransactionDate() {
        return Transaction.super.establishOpenTransactionDate();
    }

    public double getOpenPrice() {
        return openPrice;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ActiveTransaction that = (ActiveTransaction) o;
        return Double.compare(that.openPrice, openPrice) == 0 && Double.compare(that.currentPrice, currentPrice) == 0 && Objects.equals(openTransactionDate, that.openTransactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), openTransactionDate, openPrice, currentPrice);
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setOpenTransactionDate(String openTransactionDate) {
        this.openTransactionDate = openTransactionDate;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }
}

