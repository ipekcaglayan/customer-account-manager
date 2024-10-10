package com.assignment.transactionmanagement.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "createdAt")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private OffsetTime createdAt = OffsetTime.now();

    public Transaction(Long accountId, Long amount) {
        this.accountId = accountId;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return String.format("Transaction[id=%s, accountId=%s, amount=%s, createdAt=%s]",
                id, accountId, amount, createdAt);
    }
}
