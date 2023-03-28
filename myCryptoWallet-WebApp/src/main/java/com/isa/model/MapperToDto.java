package com.isa.model;

import com.isa.control.Coin;
import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperToDto {

    public static CoinDto mapCoinToCoinDto(Coin coin){
        return new CoinDto(coin.getShortSymbol()
                ,coin.getName()
                ,Double.parseDouble(coin.getLastPrice())
                ,Double.parseDouble(coin.getPriceChangePercent())
                ,Double.parseDouble(coin.getVolume()));
    }
    public static ActiveTransactionDto mapActiveTransactionToActiveTransactionDto (ActiveTransaction activeTransaction){
        return new ActiveTransactionDto(activeTransaction.getIdTransaction()
                ,mapCoinToCoinDto(activeTransaction.getCoin())
                ,activeTransaction.getVolume()
                ,activeTransaction.getOpenPrice()
                ,activeTransaction.getCurrentPrice()
                ,activeTransaction.getStopLoss()
                ,activeTransaction.getTakeProfit());
    }

    public static ClosedTransactionDto mapClosedTransactionToClosedTransactionDto(ClosedTransaction closedTransaction){
        return new ClosedTransactionDto(mapCoinToCoinDto(closedTransaction.getCoin())
                ,closedTransaction.getVolume()
                ,closedTransaction.getOpenPrice()
                ,closedTransaction.getClosePrice());
    }

    public static WalletDto mapWalletToWalletDto(Wallet wallet){
        Set<ActiveTransactionDto> activeTransactionsDto = new HashSet<>();
        Set<ClosedTransactionDto> closedTransactionDtos = new HashSet<>();
        if (!wallet.getActiveTransactions().isEmpty()) {
            activeTransactionsDto = wallet.getActiveTransactions()
                    .stream()
                    .map(MapperToDto::mapActiveTransactionToActiveTransactionDto)
                    .collect(Collectors.toSet());
        }
        if (!wallet.getTransactionsHistory().isEmpty()) {
            closedTransactionDtos = wallet.getTransactionsHistory()
                    .stream()
                    .map(MapperToDto::mapClosedTransactionToClosedTransactionDto)
                    .collect(Collectors.toSet());
        }
        return new WalletDto(wallet.getWalletId()
                ,wallet.getStartBalance()
                ,wallet.getWalletSum()
                ,wallet.getProfitLoss()
                ,wallet.getHistoricalProfitLoss()
                ,wallet.getTransactionsCosts()
                ,wallet.getWalletBalance()
                ,closedTransactionDtos
                ,activeTransactionsDto);
    }
}
