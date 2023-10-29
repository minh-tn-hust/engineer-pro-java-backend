package com.engineerpro.booking.repositories.ticket;

import com.engineerpro.booking.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketTypeRepo extends JpaRepository<TicketType, Integer> {
    TicketType getTicketTypeById(Integer id);

    TicketType getTicketTypeByType(String type);
}
