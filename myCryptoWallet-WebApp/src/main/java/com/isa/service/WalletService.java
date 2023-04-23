package com.isa.service;

import com.isa.control.Coin;
import com.isa.control.CoinSearch;
import com.isa.control.Data;
import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.model.ActiveTransactionDto;
import com.isa.model.ClosedTransactionDto;
import com.isa.model.MapperToDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WalletService {

    private Wallet wallet;
    private Coin coinForBuy = new Coin();
    private List<Coin> searchResult = new ArrayList<>();
    private ActiveTransaction transactionForClose;
    private ActiveTransaction transactionForChangeAttributes;

    public WalletService(){
        Wallet wallet = Data.deserializeWallet();
        this.wallet = wallet;
    }

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
    public void searchCoin(String coinSymbol){
        CoinSearch coinSearch = new CoinSearch();
        this.searchResult = coinSearch.search(coinSymbol);
    }

    public void addCoinForBuy(String coinSymbol){
        this.coinForBuy = searchResult.stream().filter(n->n.getSymbol().equals(coinSymbol)).findFirst().orElseThrow();
    }

    public void searchTransactionForClose(long transactionId){
        this.transactionForClose = wallet.searchActiveTransaction(transactionId);
    }

    public void searchTransactionForChangeAttributes(long transactionId){
        this.transactionForChangeAttributes = wallet.searchActiveTransaction(transactionId);
    }

    public void closeTransaction(double volume){
        this.wallet.closeActiveTransaction(transactionForClose, volume);
        this.transactionForClose = new ActiveTransaction();
    }

    public void setSlAndTpAlarm(double stopLoss, double takeProfit){
        transactionForChangeAttributes.setSLAlarm(stopLoss, true);
        transactionForChangeAttributes.setTPAlarm(takeProfit, true);
        wallet.getActiveTransactions().removeIf(n -> n.getIdTransaction() == transactionForChangeAttributes.getIdTransaction());
        wallet.getActiveTransactions().add(transactionForChangeAttributes);
        transactionForChangeAttributes = new ActiveTransaction();
    }

    public void TopUpWallet(double amount){
        wallet.loadWalletBalance(amount);
        wallet.updateWallet();
    }

    public void saveWalletToFile(){
        wallet.updateWallet();
        Data.serializer(wallet, "wallet.json");
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Coin getCoinForBuy() {
        return coinForBuy;
    }

    public void setCoinForBuy(Coin coinForBuy) {
        this.coinForBuy = coinForBuy;
    }

    public List<Coin> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Coin> searchResult) {
        this.searchResult = searchResult;
    }

    public ActiveTransaction getTransactionForClose() {
        return transactionForClose;
    }

    public void setTransactionForClose(ActiveTransaction transactionForClose) {
        this.transactionForClose = transactionForClose;
    }

    public ActiveTransaction getTransactionForChangeAttributes() {
        return transactionForChangeAttributes;
    }

    public void setTransactionForChangeAttributes(ActiveTransaction transactionForChangeAttributes) {
        this.transactionForChangeAttributes = transactionForChangeAttributes;
    }
}
