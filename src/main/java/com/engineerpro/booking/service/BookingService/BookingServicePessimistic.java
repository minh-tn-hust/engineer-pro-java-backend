package com.engineerpro.booking.service.BookingService;


import com.engineerpro.booking.models.Ticket;
import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.repositories.ticket.ITicketTypeRepo;
import com.engineerpro.booking.repositories.transaction.ITransactionRepo;
import com.engineerpro.booking.repositories.user.IUserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class BookingServicePessimistic extends BookingService {
    public BookingServicePessimistic(ITicketRepo ticketRepo, ITicketTypeRepo ticketTypeRepo, ITransactionRepo transactionRepo, IUserRepo userRepo, EntityManager entityManager) {
        super(ticketRepo, ticketTypeRepo, transactionRepo, userRepo, entityManager);
    }

    @Override
    public void processBookingTicket(Ticket ticket) {
        entityManager.lock(ticket, LockModeType.PESSIMISTIC_WRITE);
        ticket.setIsAvailable(false);
        entityManager.merge(ticket);
        entityManager.lock(ticket, LockModeType.NONE);
    }
}
