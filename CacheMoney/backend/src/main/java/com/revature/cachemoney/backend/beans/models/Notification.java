package com.revature.cachemoney.backend.beans.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Mika Nelson, Dylan Wilson, Cullen Kuch, Max Hilken, Tyler Seufert
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "Notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int notif_id;
    @Column
    private String subject;
    @Column
    private String notif_text;
    @Column
    private int user_id;
    @Column
    private boolean has_read;
    @Column
    private String date;
}
