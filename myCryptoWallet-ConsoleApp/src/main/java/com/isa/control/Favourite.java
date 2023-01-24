package com.isa.control;

import com.isa.control.Coin;
import com.isa.control.CoinsList;
import com.isa.control.Data;

import java.util.*;
import java.util.stream.Collectors;

public class Favourite extends CoinsList {

    private final List<Coin> coins;

    public Favourite(List<Coin> coins, int recordsPerPage) {
       super(coins, recordsPerPage);
       this.coins = coins;

    }
    public void AddYourFavouriteToken(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz szukaną frazę");
        String string = scanner.nextLine().toUpperCase();
        List<Coin> temporaryList = Arrays.stream(Data.deserializeCoin()).toList()
                .stream().takeWhile(n -> n.getSymbol().contains(string))
                .collect(Collectors.toList());

        if (!temporaryList.isEmpty()){
            temporaryList.forEach(n-> System.out.println(n.getSymbol() + " - " + n.getLastPrice() + " USD"));
            System.out.println("czy chcesz dodać wybrane coiny do ulubionych? [Y/N]");
            char[] choice = scanner.nextLine().toUpperCase().toCharArray();
            if (choice[0] == 'Y') {
                coins.addAll(temporaryList);
                Collections.sort(coins);
                Data.serializer(coins,"favourite.json");
            }
        }

    }
}
