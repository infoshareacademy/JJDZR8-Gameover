package com.isa.control;

import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;
import com.isa.control.transactions.TransactionsHistory;

import java.util.Set;

public class Wallet {
    private Integer walletId;
    private Integer coinValue;
    private double walletSum;
    private Coin coin;
    private Set<TransactionsHistory> transactionsHistory;
    private Set<ActiveTransaction> activeTransactions;

    public void buyNewToken(Coin coin, double volume){
        activeTransactions.add(new ActiveTransaction(coin, volume));
    }
    public void closeActiveTransaction(ActiveTransaction transaction, double volume){
        if(transaction.getVolume()<=volume){
            ClosedTransaction closed = new ClosedTransaction(transaction);
            TransactionsHistory history = new TransactionsHistory(walletId, closed);
            transactionsHistory.add(history);
            activeTransactions.remove(transaction);

        } else if (transaction.getVolume()>volume && volume>0) {
            ClosedTransaction closed = new ClosedTransaction(transaction, volume);
            TransactionsHistory history = new TransactionsHistory(walletId, closed);
            transactionsHistory.add(history);
            activeTransactions.remove(transaction);
            activeTransactions.add(closed.getActivePartOfClosedTransaction());
        }
        else System.out.println("volumen musi być liczbą dodatnią");

    }
    public void currentProfitCount(){
        this.walletSum = activeTransactions.stream().mapToDouble(n ->{
            n.refreshPrice();
            return n.countProfit();
        }).sum();
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

    public double getWalletSum() {
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
