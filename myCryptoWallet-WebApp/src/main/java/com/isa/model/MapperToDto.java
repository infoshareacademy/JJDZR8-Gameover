package com.isa.model;

import com.isa.control.Coin;
import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MapperToDto {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperToDto.class.getName());

    public static CoinDto mapCoinToCoinDto(Coin coin){
        if (coin.getShortSymbol() == null || coin.getName() == null){
            coin.creatNameAndShortSymbolForCoin();
        }
        if (coin.getLastPrice() == null || coin.getVolume() == null || coin.getPriceChangePercent() == null){
            CoinDto coinDto = new CoinDto(coin.getShortSymbol(), coin.getName(), 0,0,0);
            LOGGER.debug("{} mapped to Coin DTO", coin.getName());
            return coinDto;
        }else {
            CoinDto coinDto = new CoinDto(coin.getShortSymbol()
                    , coin.getName()
                    , Double.parseDouble(coin.getLastPrice())
                    , Double.parseDouble(coin.getPriceChangePercent())
                    , Double.parseDouble(coin.getVolume()));
            LOGGER.debug("{} mapped to Coin DTO", coin.getName());
            return coinDto;
        }
       // Todo modify this map method later to simple form without "null".

    }
    public static ActiveTransactionDto mapActiveTransactionToActiveTransactionDto (ActiveTransaction activeTransaction){
        ActiveTransactionDto activeTransactionDto = new ActiveTransactionDto(activeTransaction.getIdTransaction()
                ,mapCoinToCoinDto(activeTransaction.getCoin())
                ,activeTransaction.getVolume()
                ,activeTransaction.getOpenPrice()
                ,activeTransaction.getCurrentPrice()
                ,activeTransaction.getStopLoss()
                ,activeTransaction.getTakeProfit()
                ,activeTransaction.countProfit()
                ,activeTransaction.countTransactionCost());
        LOGGER.debug("Active transaction {} mapped to DTO", activeTransaction.getIdTransaction());
        return activeTransactionDto;
    }

    public static ClosedTransactionDto mapClosedTransactionToClosedTransactionDto(ClosedTransaction closedTransaction){
        ClosedTransactionDto closedTransactionDto = new ClosedTransactionDto(mapCoinToCoinDto(closedTransaction.getCoin())
                ,closedTransaction.getVolume()
                ,closedTransaction.getOpenPrice()
                ,closedTransaction.getClosePrice()
                ,closedTransaction.countProfit());
        LOGGER.debug("Closet transaction id: {} mapped to DTO", closedTransaction.getIdTransaction());
        return closedTransactionDto;
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
        WalletDto walletDto = new WalletDto(wallet.getWalletId()
                ,wallet.getWalletSum()
                ,wallet.getProfitLoss()
                ,wallet.getHistoricalProfitLoss()
                ,wallet.getTransactionsCosts()
                ,wallet.getWalletBalance()
                ,closedTransactionDtos
                ,activeTransactionsDto);
        LOGGER.debug("Wallet mapped to DTO");
        return walletDto;
    }
}
