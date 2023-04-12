package com.isa.model;

import java.util.Set;

public record WalletDto(String walletId, double walletSum, double profitLoss, double historicalProfitLoss, double transactionCosts, double walletBalance, Set<ClosedTransactionDto> transactionHistoryDtos, Set<ActiveTransactionDto> activeTransactionDtos) {
}
