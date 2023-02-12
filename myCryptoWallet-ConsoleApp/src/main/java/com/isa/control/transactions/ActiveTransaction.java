package com.isa.control.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.control.Coin;
import com.isa.control.Data;
import com.isa.control.Endpoints;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ActiveTransaction extends Transactions implements Transaction{

    private final String openTransactionDate;
    private final double openPrice;
    private double currentPrice;

    public ActiveTransaction(Coin coin, double volume) {
        super(coin, true, volume, Transaction.newDate.getTime());
        this.openPrice = Double.parseDouble(coin.getLastPrice());
        this.currentPrice = Double.parseDouble(coin.getLastPrice());
        this.openTransactionDate = setOpenTransactionDate();
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
    public void  refreshPrice(){ // #TODO - sprawdzić czy to działa?
       if(checkEndpointsName()){
           String request = Endpoints.buildRequest(getCoin().getShortSymbol());
           String response = Data.sendHttpRequest(request);
           if(response.contains("\"code\":-1100")) {
               System.out.println("cena nie została zaktualizowana");
           }else {
               Gson gson = new GsonBuilder().setPrettyPrinting().create();
               Coin coin = gson.fromJson(response, Coin.class);
                this.currentPrice = Double.parseDouble(coin.getLastPrice());
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

    public String getOpenTransactionDate() {
        return openTransactionDate;
    }

    @Override
    public String setOpenTransactionDate() {
        return Transaction.super.setOpenTransactionDate();
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
}
