package com.engineerpro.booking.repositories.ticket;

import com.engineerpro.booking.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITicketRepo extends JpaRepository<Ticket, Integer> {
    Ticket getTicketById(Integer id);
}
