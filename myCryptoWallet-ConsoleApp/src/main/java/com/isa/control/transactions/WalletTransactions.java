package com.isa.control.transactions;

import java.util.Objects;

public class WalletTransactions {

   private String walletId;
   private Transactions transactions;
   public WalletTransactions(){}
   public WalletTransactions(String walletId, Transactions transactions){
        this.walletId = walletId;
        this.transactions  = transactions;
   }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletTransactions that = (WalletTransactions) o;
        return Objects.equals(walletId, that.walletId) && Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletId, transactions);
    }
}
