package com.engineerpro.booking.service.BookingService;

import com.engineerpro.booking.models.Ticket;
import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.repositories.ticket.ITicketTypeRepo;
import com.engineerpro.booking.repositories.transaction.ITransactionRepo;
import com.engineerpro.booking.repositories.user.IUserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceOptimistic extends BookingService{

    public BookingServiceOptimistic(ITicketRepo ticketRepo, ITicketTypeRepo ticketTypeRepo, ITransactionRepo transactionRepo, IUserRepo userRepo, EntityManager entityManager) {
        super(ticketRepo, ticketTypeRepo, transactionRepo, userRepo, entityManager);
    }

    @Override
    public void processBookingTicket(Ticket ticket) {
        entityManager.lock(ticket, LockModeType.OPTIMISTIC);
        ticket.setIsAvailable(false);
        entityManager.merge(ticket);
        entityManager.lock(ticket, LockModeType.NONE);
    }
}
