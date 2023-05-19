package com.isa.mapper;

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
        closedTransaction.setCoin(CoinEntityMapper.mapCoinEntityToCoin(closedTransactionEntity.getCoinEntity()));
        return closedTransaction;
    }
}
