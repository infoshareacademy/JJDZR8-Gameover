package com.isa.control.transactions;

import com.isa.control.Coin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClosedTransaction extends Transactions{
    private String closeTransactionDate;
    private double closePrice;


    public ClosedTransaction(ActiveTransaction activeTransaction) {
        super(activeTransaction.getCoin(), false, activeTransaction.getVolume(), activeTransaction.getIdTransaction());
    }
}
