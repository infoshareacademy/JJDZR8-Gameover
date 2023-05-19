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
@Table(name = "Coins")
public class CoinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "shortSymbol", nullable = false)
    private String shortSymbol;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "coinEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActiveTransactionEntity> activeTransactionEntity;

    @OneToMany(mappedBy = "coinEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClosedTransactionEntity> closedTransactionEntity;

}
