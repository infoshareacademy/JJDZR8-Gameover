package com.isa;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Wallet {
    Integer walletId;
    Integer coinValue;
    Integer walletSum;
    Coin coin;
    Transaction transaction;
    User user1 = new User("Bogus");
    private static final AtomicInteger id = new AtomicInteger();
    static Map<User, List<Transaction>> userTransaction = new HashMap<>();

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

    public static void userWallet(User user, Coin... coin) {

        Coin coin1 = new Coin();
        Transaction transaction = new Transaction(id.addAndGet(1));
        Arrays.asList(coin).forEach(transaction::addQuantitiOfCoins);

        if (userTransaction.containsKey(user)) {
            userTransaction.get(user).add(transaction);
        } else {
            List<Transaction> transacionList = new ArrayList<>();
            transacionList.add(transaction);
            userTransaction.put(user, transacionList);
        }

    }
    public static List<Transaction> getAllTransactionForUser(User user){
        return userTransaction.get(user);
    }
}
