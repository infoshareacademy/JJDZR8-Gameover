package com.isa.boundary;

import com.isa.control.*;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.WalletTransactions;
import com.isa.menu.Balance;
import com.isa.menu.Menu;

import java.util.Date;
import java.util.List;
import java.util.Set;


public class App {


    public static void main(String[] args) {

        new Endpoints();
       // new Coins();



      //  Menu.getMenu();

        Coin[] coins = Data.deserializeCoin();
        System.out.println("długość list " + coins.length);

        Coin coin = coins[0];

        System.out.println(coin.getName() + coin.getLastPrice());
        ActiveTransaction at  = new ActiveTransaction(coin, 1.0);

        at.printDetails();
        at.refreshPrice();
        at.printDetails();
        Wallet wallet = new Wallet("mojWallet", Balance.THOUSAND);

        wallet.setWalletTransactions(Data.deserializeWalletTransactions());
        wallet.buyNewToken(coin,0.5);
        Set<WalletTransactions> walletTransactions = wallet.addTransactionsToRecord();
        Data.serializer(walletTransactions, "wallet.json");

        /*Coin[] coinList = Data.deserializeCoin("availableCoins.json");

        for (Coin coin : coinList) {
            coin.creatNameAndShortSymbolForCoin();
        }
        for (Coin coin : coinList) {
            System.out.println(coin.getShortSymbol() +" | " + coin.getName() + " " + coin.getLastPrice());
        }*/


    }

}

