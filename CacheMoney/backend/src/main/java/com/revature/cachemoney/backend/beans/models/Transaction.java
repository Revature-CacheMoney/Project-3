package com.revature.cachemoney.backend.beans.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;

    @Column(name = "account_id")
    private Integer account_id;

    @Column(name = "description")
    private String description;

    // look into date datatype, and how to/accept formatting...
    @Column(name= "transaction_date")
    private Date transaction_date;

    // look into datatype to take care of floating point arithmetic
    @Column(name = "transaction_amount")
    private Double transaction_amount;

    @Column(name = "ending_balance")
    private Double ending_balance;

    public Transaction(Integer transaction_id, Integer account_id, String description, Date transaction_date, Double transaction_amount, Double ending_balance) {
        this.transaction_id = transaction_id;
        this.account_id = account_id;
        this.description = description;
        this.transaction_date = transaction_date;
        this.transaction_amount = transaction_amount;
        this.ending_balance = ending_balance;
    }

    @Override
    public String toString() {
        String s = "";
        s += "TRANSACTION";
        s += "{";
        s += this.transaction_id + "\n";
        s += this.account_id + "\n";
        s += this.description + "\n";
        s += this.transaction_date + "\n";
        s += this.transaction_amount + "\n";
        s += this.ending_balance + "\n";
        s += "}";

        return s;    }
}
