package com.isa.service;

import com.isa.control.Data;
import com.isa.control.Wallet;
import com.isa.menu.Balance;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class WalletService {

    private Map<String, Wallet> walletsMap;

    public WalletService(){
       this.walletsMap = Data.deserializeWallet();
    }

    public Set<String> getAllWalletsId(){
        return walletsMap.keySet();
    }

    public Wallet findWalletById(String walletId){
        if(walletsMap.containsKey(walletId)){
            return walletsMap.get(walletId);
        }else return new Wallet();
    }

    public void addWalletToWalletsMap(Wallet wallet){
        walletsMap.put(wallet.getWalletId(), wallet);
    }

    public boolean isNewWalletIdExist(String walletId){
        if (walletsMap.containsKey(walletId)) return true;
        else return false;
    }

    public Map<String, Wallet> getWalletsMap() {
        return walletsMap;
    }
}
