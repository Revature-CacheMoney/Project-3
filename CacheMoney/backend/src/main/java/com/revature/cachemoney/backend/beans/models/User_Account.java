package com.revature.cachemoney.backend.beans.models;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.persistence.*;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users_accounts")
public class User_Account {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_user_id")
    private Integer UserID;

    @Column(name = "accounts_account_id")
    private String AccountID;

 // bi-directional many-to-one association to User
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_user_id", insertable = false, updatable = false)
    private User user;
    
    // bi-directional many-to-one association to Itinerary
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "accounts_account_id", insertable = false, updatable = false)
    private Account account;
}
