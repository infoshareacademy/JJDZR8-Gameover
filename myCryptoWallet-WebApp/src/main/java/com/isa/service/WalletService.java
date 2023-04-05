package com.isa.service;

import com.isa.control.Coin;
import com.isa.control.CoinSearch;
import com.isa.control.Data;
import com.isa.control.Wallet;
import com.isa.menu.Balance;
import com.isa.model.ActiveTransactionDto;
import com.isa.model.ClosedTransactionDto;
import com.isa.model.MapperToDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.isa.model.MapperToDto.mapActiveTransactionToActiveTransactionDto;
import static com.isa.model.MapperToDto.mapWalletToWalletDto;

@Service
public class WalletService {

    private Wallet wallet;

    public WalletService(){
        Map<String, Wallet> stringWalletMap = Data.deserializeWallet();
        this.wallet = stringWalletMap.get("mojWallet");
    }

 /*   public Set<String> getAllWalletsId(){
        return walletsMap.keySet();
    }

  */

   /* public void findWalletById(String walletId){
        if(walletsMap.containsKey(walletId)){
            this.walletById = walletsMap.get(walletId);
        }else this.walletById = new Wallet();
    }

    */

    /*public void addWalletToWalletsMap(Wallet wallet){
        walletsMap.put(wallet.getWalletId(), wallet);
    }

     */

    /*public boolean isNewWalletIdExist(String walletId){
        if (walletsMap.containsKey(walletId)) return true;
        else return false;
    }

     */
    public Set<ActiveTransactionDto> mapActiveTransactionsToDto(){
        return this.wallet.getActiveTransactions().stream()
                .map(MapperToDto::mapActiveTransactionToActiveTransactionDto).collect(Collectors.toSet());
    }

    public Set<ClosedTransactionDto> mapClosedTransactionsToDto(){
        return this.wallet.getTransactionsHistory().stream()
                .map(MapperToDto::mapClosedTransactionToClosedTransactionDto).collect(Collectors.toSet());
    }

    public void buyNewTokenForWallet(Coin coin, double volume){
        this.wallet.buyNewToken(coin, volume);
    }
    public List<Coin> searchCoin(String coinSymbol){
        CoinSearch coinSearch = new CoinSearch();
        return coinSearch.search(coinSymbol);
    }
}
