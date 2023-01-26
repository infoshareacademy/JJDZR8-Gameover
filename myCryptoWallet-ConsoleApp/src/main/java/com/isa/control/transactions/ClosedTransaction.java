package com.isa.control.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.control.Coin;
import com.isa.control.Data;
import com.isa.control.Endpoints;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClosedTransaction extends Transactions implements Transaction{
    private String closeTransactionDate;
    private double closePrice;
    private final double openPrice ;


    public ClosedTransaction(ActiveTransaction activeTransaction) {
        super(activeTransaction.getCoin(), false, activeTransaction.getVolume(), activeTransaction.getIdTransaction());
        this.closeTransactionDate = setCloseTransactionDate();
        this.openPrice = activeTransaction.getOpenPrice();
    }

    public ClosedTransaction(ActiveTransaction activeTransaction, double volume){
        super(activeTransaction.getCoin(), false,volume, activeTransaction.getIdTransaction() );
        this.closeTransactionDate = setCloseTransactionDate();
        this.openPrice = activeTransaction.getOpenPrice();

        new ActiveTransaction(activeTransaction, activeTransaction.getVolume() - volume);
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
                Coin coin = gson.fromJson(response, Coin.class);
                closePrice = Double.parseDouble(coin.getLastPrice());
            }
        }

    }

    @Override
    public String setCloseTransactionDate() {
        return Transaction.super.setCloseTransactionDate();
    }
}
