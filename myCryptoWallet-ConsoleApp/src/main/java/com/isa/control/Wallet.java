package com.isa.control;

import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;
import com.isa.control.transactions.WalletTransactions;
import com.isa.menu.Balance;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Wallet {
    private String walletId;
    private Balance startBalance;
    private Integer coinValue;
    private double walletSum;
    private double profitLoss;
    private double historicalProfitLoss;
    private double availableFunds;
    private double walletBalance;
    private Coin coin;
    private Set<ClosedTransaction> transactionsHistory;
    private Set<ActiveTransaction> activeTransactions;

    public Wallet(String walletId, Balance startBalance){
        setWalletTransactions(Data.deserializeWalletTransactions());
    }

    public void buyNewToken(Coin coin, double volume){
        activeTransactions.add(new ActiveTransaction(coin, volume));
    }
    public void closeActiveTransaction(ActiveTransaction transaction, double volume){
        if(transaction.getVolume()<=volume){
            ClosedTransaction closed = new ClosedTransaction(transaction);
            transactionsHistory.add(closed);
            activeTransactions.remove(transaction);

        } else if (transaction.getVolume()>volume && volume>0) {
            ClosedTransaction closed = new ClosedTransaction(transaction, volume);
            transactionsHistory.add(closed);
            activeTransactions.remove(transaction);
            activeTransactions.add(closed.getActivePartOfClosedTransaction());
        }
        else System.out.println("volumen musi być liczbą dodatnią");

    }
    public void currentProfitCount(){
        this.profitLoss = activeTransactions.stream().mapToDouble(n ->{
            n.refreshPrice();
            return n.countProfit();
        }).sum();
    }

    public double historyProfitCount(){
        return transactionsHistory.stream().mapToDouble(ClosedTransaction::countProfit).sum();
    }

    public String getWalletId() {
        return walletId;
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

    public void setWalletSum(double walletSum) {
        this.walletSum = walletSum;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }
    public void setWalletTransactions(Set<WalletTransactions> transactionsList){
        transactionsList.stream().filter(n->n.walletId().equals(walletId))
                .forEach(n->{
            if (n.transactions().isActive()) this.activeTransactions.add((ActiveTransaction) n.transactions());
            else this.transactionsHistory.add((ClosedTransaction) n.transactions());
        });
    }
    public Set<WalletTransactions> addTransactionsToRecord(){
        Set<WalletTransactions> records = new HashSet<>();
         transactionsHistory.forEach(n->records.add(new WalletTransactions(walletId, n)));
         activeTransactions.forEach(n->records.add(new WalletTransactions(walletId, n)));
         return records;
    }
}
