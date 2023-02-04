package com.isa.control;

import com.isa.control.Coin;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;
import com.isa.control.transactions.Transactions;
import com.isa.control.transactions.TransactionsHistory;

import java.util.Set;

public class Wallet {
    private Integer walletId;
    private Integer coinValue;
    private Integer walletSum;
    private Coin coin;
    private Set<TransactionsHistory> transactionsHistory;
    private Set<Transactions> activeTransactions;

    public void buyNewToken(Coin coin, double volume){
        activeTransactions.add(new ActiveTransaction(coin, volume));
    }
    public void closeActiveTransaction(Transactions transactions, double volume){
        if (!transactions.isActive()) System.out.println("Transakcja jest już zamknięta");
        else {
            if(transactions.getVolume()<=volume){
                ClosedTransaction closed = new ClosedTransaction((ActiveTransaction) transactions);
                TransactionsHistory history = new TransactionsHistory(walletId, closed);
                transactionsHistory.add(history);
                activeTransactions.remove(transactions);

            } else if (transactions.getVolume()>volume && volume>0) {
                ClosedTransaction closed = new ClosedTransaction((ActiveTransaction) transactions, volume);
                TransactionsHistory history = new TransactionsHistory(walletId, closed);
                transactionsHistory.add(history);
                activeTransactions.remove(transactions);
                activeTransactions.add(closed.getActivePartOfClosedTransaction());
            }
            else System.out.println("volumen musi być liczbą dodatnią");
        }

    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(Integer coinValue) {
        this.coinValue = coinValue;
    }

    public Integer getWalletSum() {
        return walletSum;
    }

    public void setWalletSum(Integer walletSum) {
        this.walletSum = walletSum;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }
}
