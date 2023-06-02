package com.isa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wallet")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_name", nullable = false)
    private String walletName;

    @Column(name = "historical_profit_loss")
    private double historicalProfitLoss;

    @Column(name="paymentCalc", nullable = false)
    private double paymentCalc;

    @OneToOne()
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "walletEntity", cascade = CascadeType.ALL)
    private List<ActiveTransactionEntity> activeTransactionEntityList;

    @OneToMany(mappedBy = "walletEntity", cascade = CascadeType.ALL)
    private List<ClosedTransactionEntity> closedTransactionEntities;


    public void addActiveTransactionEntity(ActiveTransactionEntity activeTransactionEntity){
        activeTransactionEntity.setWalletEntity(this);
        this.activeTransactionEntityList.add(activeTransactionEntity);
    }

    public void addActiveTransactionsEntityList(List<ActiveTransactionEntity> activeTransactionEntityList){
        for (ActiveTransactionEntity activeTransaction : activeTransactionEntityList) {
            this.addActiveTransactionEntity(activeTransaction);
        }
    }

    public void addClosedTransactionEntity(ClosedTransactionEntity closedTransactionEntity){
        closedTransactionEntity.setWalletEntity(this);
        this.closedTransactionEntities.add(closedTransactionEntity);
    }

    public void addClosedTransactionsEntityList(List<ClosedTransactionEntity> closedTransactionEntityList){
        for (ClosedTransactionEntity closedTransaction : closedTransactionEntityList){
            this.addClosedTransactionEntity(closedTransaction);
        }
    }

    /*@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users",
            joinColumns = @JoinColumn(
                    name = "walletid_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "wallet_id", referencedColumnName = "wallet_id")
            )*/
}

