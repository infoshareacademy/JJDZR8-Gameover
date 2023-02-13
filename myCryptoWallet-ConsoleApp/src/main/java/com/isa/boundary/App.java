package com.isa.boundary;

import com.isa.control.Coin;
import com.isa.control.Coins;
import com.isa.control.Data;
import com.isa.control.Endpoints;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.menu.Balance;
import com.isa.menu.Menu;

import java.util.Date;
import java.util.List;



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

        /*Coin[] coinList = Data.deserializeCoin("availableCoins.json");

        for (Coin coin : coinList) {
            coin.creatNameAndShortSymbolForCoin();
        }
        for (Coin coin : coinList) {
            System.out.println(coin.getShortSymbol() +" | " + coin.getName() + " " + coin.getLastPrice());
        }*/


    }

}

