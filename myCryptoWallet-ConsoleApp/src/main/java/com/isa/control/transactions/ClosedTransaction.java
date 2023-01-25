package com.isa.control.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClosedTransaction extends Transactions{
    private String closeTransactionDate;
    private double closePrice;
    private final Date newDate = new Date();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
