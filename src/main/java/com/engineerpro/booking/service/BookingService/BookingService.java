package com.engineerpro.booking.service.BookingService;

import com.engineerpro.booking.BookingApplication;
import com.engineerpro.booking.models.Ticket;
import com.engineerpro.booking.models.TicketType;
import com.engineerpro.booking.models.Transaction;
import com.engineerpro.booking.models.User;
import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.repositories.ticket.ITicketTypeRepo;
import com.engineerpro.booking.repositories.transaction.ITransactionRepo;
import com.engineerpro.booking.repositories.user.IUserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
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
    protected final static Integer RANDOM_SEED = 88234;

    protected Ticket getRandomTicket(List<Ticket> ticket) {
        Random generator = new Random(RANDOM_SEED);
        int index = Math.abs(generator.nextInt());

        return ticket.get(76);
    }

    @Transactional
    public abstract void processBookingTicket(Ticket ticket);

    @Transactional
    public void createTransaction(User user, Ticket ticket) {
       Transaction newTransaction = new Transaction(user, ticket);
       transactionRepo.save(newTransaction);
    }

    @Transactional
    public void userBookingTicket(Integer userId, String ticketType) {
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
            System.out.println("[NOR Exception] " + exception.getMessage() );
        }
    }

    public void userCancelTicket(Integer userId, Integer ticketId) {

    }
}
