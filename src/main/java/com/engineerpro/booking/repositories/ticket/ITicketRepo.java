package com.engineerpro.booking.repositories.ticket;

import com.engineerpro.booking.models.Ticket;
import com.engineerpro.booking.models.TicketType;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ITicketRepo extends JpaRepository<Ticket, Integer> {
    Ticket getTicketById(Integer id);
    List<Ticket> getTicketsByTypeAndIsAvailable(TicketType type, @NonNull Boolean isAvailable);

}
