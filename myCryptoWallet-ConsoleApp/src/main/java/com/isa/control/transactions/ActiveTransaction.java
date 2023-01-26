package com.isa.control.transactions;

import com.isa.control.Coin;
import com.isa.control.Endpoints;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActiveTransaction extends Transactions implements Transaction{

    private String openTransactionDate;
    private final double openPrice;
    private double currentPrice;

    public ActiveTransaction(Coin coin, double volume) {
        super(coin, true, volume, Transaction.newDate.getTime() );
        this.openPrice = Double.parseDouble(coin.getLastPrice());
        this.currentPrice = Double.parseDouble(coin.getLastPrice());
        setOpenTransactionDate();
    }

    @Override
    public double countProfit() {
        return (currentPrice - openPrice) * getVolume();
    }
    public void  refreshCurrentPrice(){
       if(Endpoints.getCoinsNames().containsKey(getCoin().getSymbol())){

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


}
