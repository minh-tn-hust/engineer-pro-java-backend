package com.engineerpro.booking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private TicketType type;

    @NonNull
    @Column(name = "is_available")
    private Boolean isAvailable;

    @Version
    private Integer version;

    public Ticket(TicketType newTicketType) {
        type = newTicketType;
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", type=" + type +
                ", isAvailable=" + isAvailable +
                ", version=" + version +
                '}';
    }
}
