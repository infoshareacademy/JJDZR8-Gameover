package com.isa.control.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Transaction {
    Date newDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    double countProfit();
    void printDetails();
    void refreshPrice();
    boolean checkEndpointsName();
    default String establishCloseTransactionDate(){
        return dateFormat.format(new Date().getTime());
    }
    default String establishOpenTransactionDate(){
        return dateFormat.format(new Date().getTime());
    };

}
