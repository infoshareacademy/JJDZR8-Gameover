package com.isa.menu;


public enum Balance {
    THOUSAND(1000.00),
    TEN_THOUSAND(10000.00),
    HUNDRED_THOUSANDS(100000.00),
    ONE_MILLION(1000000.00);

    private double worth;

    Balance(double worth) {
        this.worth = worth;
    }

    public double getWorth() {
        return worth;
    }

    public static Balance getBalance(double worth){
        Balance balance = TEN_THOUSAND;
        for(Balance element: Balance.values()){
            if(element.getWorth() == worth) balance = element;
        }
        return balance;
    }

    public static void printBalance(){
        for(Balance element: Balance.values()){
            System.out.println(element.getWorth() + " " + "USD");
        }
    }
}
