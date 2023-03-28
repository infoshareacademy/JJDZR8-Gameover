package com.isa.model;

import com.isa.menu.Balance;

import java.util.Set;

public record WalletDto(String walletId, Balance startBalance, double walletSum, double profitLoss, double historicalProfitLoss, double transactionCosts, double walletBalance, Set<ClosedTransactionDto> transactionHistoryDtos, Set<ActiveTransactionDto> activeTransactionDtos) {
}
