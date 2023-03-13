package com.isa.service;

import com.isa.control.Data;
import com.isa.control.Wallet;
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

    public Map<String, Wallet> getWalletsMap() {
        return walletsMap;
    }
}
