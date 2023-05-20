package com.isa.mapper;

import com.isa.control.Coin;
import com.isa.control.transactions.ClosedTransaction;
import com.isa.entity.ClosedTransactionEntity;

public class ClosedTransactionEntityMapper {

    public static ClosedTransaction mapClosedTransactionEntityToClosedTransaction(ClosedTransactionEntity closedTransactionEntity){
        ClosedTransaction closedTransaction = new ClosedTransaction();
        closedTransaction.setIdTransaction(closedTransactionEntity.getIdTransaction());
        closedTransaction.setActive(closedTransactionEntity.isActive());
        closedTransaction.setVolume(closedTransactionEntity.getVolume());
        closedTransaction.setCloseTransactionDate(closedTransactionEntity.getCloseTransactionDate());
        closedTransaction.setOpenPrice(closedTransactionEntity.getOpenPrice());
        closedTransaction.setClosePrice(closedTransactionEntity.getClosePrice());
        Coin coin = new Coin();
        coin.setSymbol(closedTransactionEntity.getCoinSymbol());
        coin.creatNameAndShortSymbolForCoin();
        closedTransaction.setCoin(coin);
        return closedTransaction;
    }

    public static ClosedTransactionEntity mapClosedTransactionToEntity(ClosedTransaction closedTransaction){
        ClosedTransactionEntity closedTransactionEntity = new ClosedTransactionEntity();
        closedTransactionEntity.setIdTransaction(closedTransaction.getIdTransaction());
        closedTransactionEntity.setActive(closedTransaction.isActive());
        closedTransactionEntity.setVolume(closedTransaction.getVolume());
        closedTransactionEntity.setCloseTransactionDate(closedTransaction.getCloseTransactionDate());
        closedTransactionEntity.setOpenPrice(closedTransaction.getOpenPrice());
        closedTransactionEntity.setClosePrice(closedTransaction.getClosePrice());
        closedTransactionEntity.setCoinSymbol(closedTransaction.getCoin().getSymbol());
        return closedTransactionEntity;
    }
}
