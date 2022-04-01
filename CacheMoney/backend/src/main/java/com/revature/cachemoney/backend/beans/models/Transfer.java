package com.revature.cachemoney.backend.beans.models;

import lombok.*;

import javax.persistence.*;

/**
 * Model containing information regarding a Transfer.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transfer {
    @Id
    @GeneratedValue
    private int transfer_id;

    @OneToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account sourceAccount;

    @OneToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account destinationAccount;

    @Column
    private int amount;
}
