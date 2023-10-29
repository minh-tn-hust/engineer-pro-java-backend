package com.engineerpro.booking.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ticket_type")
public class TicketType {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "name", unique = true)
    private String type;

    public TicketType(String type, Integer amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "id=" + id +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
