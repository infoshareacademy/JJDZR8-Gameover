package com.isa.control.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Transaction {
    Date newDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    double countProfit();
    void printDetails();
    void refreshPrice(); // #TODO - sprawdzić czy to działa.
    default String setCloseTransactionDate(){
        return dateFormat.format(new Date().getTime());
    }
    default String setOpenTransactionDate(){
        return dateFormat.format(new Date().getTime());
    };

}
