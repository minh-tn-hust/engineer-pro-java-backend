package com.engineerpro.booking.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User user;

    @OneToOne
    private Ticket ticket;

    @Column(name = "is_expired")
    private Boolean isExpired;

    public Transaction(User user, Ticket ticket) {
        this.user = user;
        this.ticket = ticket;
        this.isExpired = false;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + user +
                ", ticket=" + ticket +
                ", isExpired=" + isExpired +
                '}';
    }
}
