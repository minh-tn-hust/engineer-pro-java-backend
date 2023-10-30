package com.engineerpro.booking.service.BookingService;

import com.engineerpro.booking.models.Ticket;
import com.engineerpro.booking.models.TicketType;
import com.engineerpro.booking.models.Transaction;
import com.engineerpro.booking.models.User;
import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.repositories.ticket.ITicketTypeRepo;
import com.engineerpro.booking.repositories.transaction.ITransactionRepo;
import com.engineerpro.booking.repositories.user.IUserRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

public abstract class BookingService {
    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected ITicketRepo ticketRepo;

    @Autowired
    protected ITicketTypeRepo ticketTypeRepo;

    @Autowired
    protected ITransactionRepo transactionRepo;

    @Autowired
    protected IUserRepo userRepo;

    public BookingService(
            ITicketRepo ticketRepo,
            ITicketTypeRepo ticketTypeRepo,
            ITransactionRepo transactionRepo,
            IUserRepo userRepo,
            EntityManager entityManager
    ) {
        this.ticketRepo = ticketRepo;
        this.ticketTypeRepo = ticketTypeRepo;
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
        this.entityManager = entityManager;
    }

    public Ticket getRandomTicket(List<Ticket> ticket) {
        Random generator = new Random();
        int index = Math.abs(generator.nextInt());

        return ticket.get(index % ticket.size());
    }

    abstract public void processBookingTicket(Ticket ticket);

    public void createTransaction(User user, Ticket ticket) {
       Transaction newTransaction = new Transaction(user, ticket);
       transactionRepo.save(newTransaction);
    }

    @Transactional
    public void userBookingTicketWithType(Integer userId, String ticketType) {
        try {
            TicketType type = ticketTypeRepo.getTicketTypeByType(ticketType);
            List<Ticket> listAvailable = ticketRepo.getTicketsByTypeAndIsAvailable(type, true);
            Ticket bookingTicket = getRandomTicket(listAvailable);
            this.processBookingTicket(bookingTicket);
            User user = userRepo.getUserById(userId);
            createTransaction(user, bookingTicket);
        } catch (DataAccessException daException) {
            System.out.println("[DA Exception] " + daException.getMessage());
        } catch (Exception exception)  {
            System.out.println("[NOR Exception] " + exception.getCause() );
        }
    }

    @Transactional
    public void userBookingTicketWithTicketId(Integer userId, Integer ticketId) {
        try {
            Ticket bookingTicket = ticketRepo.getTicketById(ticketId);
            this.processBookingTicket(bookingTicket);
            User user = userRepo.getUserById(userId);
            createTransaction(user, bookingTicket);
        } catch (Exception exception){
            System.out.println("[Exception] " + exception.getMessage());
        }
    }
}
