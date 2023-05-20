package com.isa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Active_Transactions")
public class ActiveTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stamp", nullable = false, unique = true)
    private long idTransaction;

    @Column(name = "is_Active", nullable = false)
    private boolean isActive;

    @Column(name = "volume", nullable = false)
    private double volume;

    @Column(name = "open_Transaction_Date", nullable = false)
    private String openTransactionDate;

    @Column(name = "open_Price", nullable = false)
    private double openPrice;

    @Column(name = "current_Price", nullable = false)
    private double currentPrice;

    @Column(name = "stop_Loss")
    private double stopLoss;

    @Column(name = "is_Sl_On")
    private boolean isSLOn;

    @Column(name = "take_Profit")
    private double takeProfit;

    @Column(name = "is_Tp_On")
    private boolean isTPOn;
    @Column(name = "coin_symbol", nullable = false)
    private String coinSymbol;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private WalletEntity walletEntity;

  /*  @ManyToOne
    @JoinColumn(name = "coin_id", nullable = false)
    private CoinEntity coinEntity;

   */
}
