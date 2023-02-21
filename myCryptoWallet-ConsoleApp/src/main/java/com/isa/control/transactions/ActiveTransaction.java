package com.isa.control.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.control.Coin;
import com.isa.control.Data;
import com.isa.control.Endpoints;

import java.util.Objects;

public class ActiveTransaction implements Transaction, Comparable<ActiveTransaction>{

    private long idTransaction;
    private Coin coin;
    private boolean isActive;
    private double volume;
    private String openTransactionDate;
    private double openPrice;
    private double currentPrice;
    private double stopLoss;
    private double takeProfit;

    public ActiveTransaction(){}

    public ActiveTransaction(Coin coin, double volume) {
        this.idTransaction = Transaction.newDate.getTime();
        this.isActive = true;
        this.coin = coin;
        this.volume = volume;
        this.openPrice = Double.parseDouble(coin.getLastPrice());
        this.currentPrice = Double.parseDouble(coin.getLastPrice());
        this.openTransactionDate = establishOpenTransactionDate();
    }

    public ActiveTransaction(ActiveTransaction activeTransaction, double volume){
        this.idTransaction = Transaction.newDate.getTime();
        this.isActive = true;
        this.coin = activeTransaction.getCoin();
        this.volume = volume;
        this.openPrice = activeTransaction.getOpenPrice();
        this.openTransactionDate = activeTransaction.getOpenTransactionDate();
        this.currentPrice = activeTransaction.getCurrentPrice();
    }

    @Override
    public double countProfit() {
        return (currentPrice - openPrice) * volume;
    }
    @Override
    public void  refreshPrice(){
       if(checkEndpointsName()){
           String request = Endpoints.buildRequest(coin.getShortSymbol());
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
        System.out.println("id Transakcji" + idTransaction);
        System.out.println(coin.getName() + " " + coin.getShortSymbol() +
                "cena zakupu " + openPrice + "cena aktualna " + currentPrice + "ilość: " + volume);
        System.out.println("Zysk/Strata: " + countProfit());

    }
    public double countTransactionCost(){
        return openPrice * volume;
    }



    @Override
    public String establishOpenTransactionDate() {
        return Transaction.super.establishOpenTransactionDate();
    }

    @Override
    public boolean checkEndpointsName() {
        return Endpoints.getCoinsNames().containsKey(coin.getShortSymbol());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveTransaction that = (ActiveTransaction) o;
        return idTransaction == that.idTransaction && isActive == that.isActive && Double.compare(that.volume, volume) == 0 && Double.compare(that.openPrice, openPrice) == 0 && Double.compare(that.currentPrice, currentPrice) == 0 && Double.compare(that.stopLoss, stopLoss) == 0 && Double.compare(that.takeProfit, takeProfit) == 0 && Objects.equals(coin, that.coin) && Objects.equals(openTransactionDate, that.openTransactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction, coin, isActive, volume, openTransactionDate, openPrice, currentPrice, stopLoss, takeProfit);
    }

    @Override
    public int compareTo(ActiveTransaction activeTransaction) {
        if(idTransaction<activeTransaction.getIdTransaction()) return 1;
        else if (idTransaction>activeTransaction.getIdTransaction()) return -1;
        else return 0;
    }

    public long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getOpenTransactionDate() {
        return openTransactionDate;
    }

    public void setOpenTransactionDate(String openTransactionDate) {
        this.openTransactionDate = openTransactionDate;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public double getTakeProfit() {
        return takeProfit;
    }

    public void setTakeProfit(double takeProfit) {
        this.takeProfit = takeProfit;
    }
}

