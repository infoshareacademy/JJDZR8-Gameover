package com.isa.control.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.control.Coin;
import com.isa.control.Data;
import com.isa.control.Endpoints;

import java.util.Objects;

public class ClosedTransaction implements Transaction, Comparable<ClosedTransaction>{

    private long idTransaction;
    private Coin coin;
    private boolean isActive;
    private double volume;
    private String closeTransactionDate;
    private double closePrice;
    private double openPrice;
    private ActiveTransaction activePartOfClosedTransaction;

    public ClosedTransaction(){}
    public ClosedTransaction(ActiveTransaction activeTransaction) {
        this.idTransaction = activeTransaction.getIdTransaction();
        this.isActive = false;
        this.volume = activeTransaction.getVolume();
        this.closeTransactionDate = establishCloseTransactionDate();
        this.openPrice = activeTransaction.getOpenPrice();
        refreshPrice();
    }

    public ClosedTransaction(ActiveTransaction activeTransaction, double volume){
        this.idTransaction = activeTransaction.getIdTransaction();
        this.isActive = false;
        this.volume = volume;
        this.closeTransactionDate = establishCloseTransactionDate();
        this.openPrice = activeTransaction.getOpenPrice();
        refreshPrice();

        this.activePartOfClosedTransaction = new ActiveTransaction(activeTransaction, activeTransaction.getVolume() - volume);
    }

    @Override
    public double countProfit() {
        return (closePrice - openPrice) * volume;
    }

    @Override
    public void printDetails() {
        System.out.println("id Transakcji" + idTransaction);
        System.out.println(coin.getName() + " " + coin.getShortSymbol() +
                "cena zakupu " + openPrice + "cena aktualna " + closePrice + "ilość: " + volume);
        System.out.println("Zysk/Strata: " + countProfit());

    }

    @Override
    public void refreshPrice() {
        if(checkEndpointsName()){
            String request = Endpoints.buildRequest(coin.getShortSymbol());
            String response = Data.sendHttpRequest(request);
            if(response.contains("\"code\":-1100")) {
                System.out.println("Nieprawidłowa cena transakcji spróbuj jeszcze raz");
            }else {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Coin[] coin = gson.fromJson(response, Coin[].class);
                this.closePrice = Double.parseDouble(coin[0].getLastPrice());
            }
        }

    }

    @Override
    public String establishCloseTransactionDate() {
        return Transaction.super.establishCloseTransactionDate();
    }



    @Override
    public boolean checkEndpointsName() {
        return Endpoints.getCoinsNames().containsKey(coin.getShortSymbol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClosedTransaction that = (ClosedTransaction) o;
        return idTransaction == that.idTransaction && isActive == that.isActive && Double.compare(that.volume, volume) == 0 && Double.compare(that.closePrice, closePrice) == 0 && Double.compare(that.openPrice, openPrice) == 0 && Objects.equals(coin, that.coin) && Objects.equals(closeTransactionDate, that.closeTransactionDate) && Objects.equals(activePartOfClosedTransaction, that.activePartOfClosedTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction, coin, isActive, volume, closeTransactionDate, closePrice, openPrice, activePartOfClosedTransaction);
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

    public String getCloseTransactionDate() {
        return closeTransactionDate;
    }

    public void setCloseTransactionDate(String closeTransactionDate) {
        this.closeTransactionDate = closeTransactionDate;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public ActiveTransaction getActivePartOfClosedTransaction() {
        return activePartOfClosedTransaction;
    }

    public void setActivePartOfClosedTransaction(ActiveTransaction activePartOfClosedTransaction) {
        this.activePartOfClosedTransaction = activePartOfClosedTransaction;
    }

    @Override
    public int compareTo(ClosedTransaction closedTransaction) {
        if(idTransaction<closedTransaction.getIdTransaction()) return 1;
        else if (idTransaction>closedTransaction.getIdTransaction()) return -1;
        else return 0;
    }
}
