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
}
