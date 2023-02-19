package com.isa.service;

import com.isa.control.Coin;
import com.isa.control.Coins;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoinService {

    public static List<Coin> getCoinList() {
        List<Coin> coinList = Coins.getInstance().getCoinList();
        System.out.println(coinList.get(2));

        return coinList;
    }

}
