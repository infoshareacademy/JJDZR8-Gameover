package com.isa.control.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.control.Coin;
import com.isa.control.Data;
import com.isa.control.Endpoints;

import java.util.Objects;

public class ClosedTransaction extends Transactions implements Transaction{
    private String closeTransactionDate;
    private double closePrice;
    private double openPrice;
    private ActiveTransaction activePartOfClosedTransaction;

    public ClosedTransaction(){}

    public ClosedTransaction(ActiveTransaction activeTransaction) {
        super(activeTransaction.getCoin(), false, activeTransaction.getVolume(), activeTransaction.getIdTransaction());
        this.closeTransactionDate = establishCloseTransactionDate();
        this.openPrice = activeTransaction.getOpenPrice();
        refreshPrice();
    }

    public ClosedTransaction(ActiveTransaction activeTransaction, double volume){
        super(activeTransaction.getCoin(), false,volume, activeTransaction.getIdTransaction() );
        this.closeTransactionDate = establishCloseTransactionDate();
        this.openPrice = activeTransaction.getOpenPrice();
        refreshPrice();

        this.activePartOfClosedTransaction = new ActiveTransaction(activeTransaction, activeTransaction.getVolume() - volume);
    }

    @Override
    public double countProfit() {
        return (closePrice - openPrice) * getVolume();
    }

    @Override
    public void printDetails() {
        System.out.println("id Transakcji" + getIdTransaction());
        System.out.println(getCoin().getName() + " " + getCoin().getShortSymbol() +
                "cena zakupu " + openPrice + "cena aktualna " + closePrice + "ilość: " + getVolume());
        System.out.println("Zysk/Strata: " + countProfit());

    }

    @Override
    public void refreshPrice() {
        if(checkEndpointsName()){
            String request = Endpoints.buildRequest(getCoin().getShortSymbol());
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

    public String getCloseTransactionDate() {
        return closeTransactionDate;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public ActiveTransaction getActivePartOfClosedTransaction() {
        return activePartOfClosedTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClosedTransaction that = (ClosedTransaction) o;
        return Double.compare(that.closePrice, closePrice) == 0 && Double.compare(that.openPrice, openPrice) == 0 && Objects.equals(closeTransactionDate, that.closeTransactionDate) && Objects.equals(activePartOfClosedTransaction, that.activePartOfClosedTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(closeTransactionDate, closePrice, openPrice, activePartOfClosedTransaction);
    }

    public void setCloseTransactionDate(String closeTransactionDate) {
        this.closeTransactionDate = closeTransactionDate;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public void setActivePartOfClosedTransaction(ActiveTransaction activePartOfClosedTransaction) {
        this.activePartOfClosedTransaction = activePartOfClosedTransaction;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }
}
