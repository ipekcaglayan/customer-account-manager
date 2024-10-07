package com.assignment.accountmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "createdAt")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private Long balance = 0L;

    @Column(nullable = false) // Timestamp cannot be null
    private OffsetTime createdAt = OffsetTime.now();

    public Account(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return String.format("Account[id=%s, balance=%s, createdAt=%s]",
                id, balance, createdAt);
    }
}
