package com.engineerpro.booking.services;

import com.engineerpro.booking.BookingApplication;
import com.engineerpro.booking.models.Ticket;
import com.engineerpro.booking.models.TicketType;
import com.engineerpro.booking.models.Transaction;
import com.engineerpro.booking.models.User;
import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.repositories.ticket.ITicketTypeRepo;
import com.engineerpro.booking.repositories.transaction.ITransactionRepo;
import com.engineerpro.booking.repositories.user.IUserRepo;

import com.engineerpro.booking.service.BookingService.BookingService;

import com.engineerpro.booking.service.BookingService.BookingServiceOptimistic;
import com.engineerpro.booking.service.TicketService;
import com.engineerpro.booking.service.UserService;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookingServiceTest {
    @InjectMocks
    private BookingService bookingService ;

    @Mock
    private ITicketRepo ticketRepo;

    @Mock
    private ITicketTypeRepo ticketTypeRepo;

    @Mock
    private ITransactionRepo transactionRepo;

    @Mock
    private IUserRepo userRepo;

    @BeforeAll
    public static void beginSetup() {

    }

    @BeforeEach
    public void setUp() {
        ticketRepo = Mockito.mock(ITicketRepo.class);
        ticketTypeRepo = Mockito.mock(ITicketTypeRepo.class);
        transactionRepo = Mockito.mock(ITransactionRepo.class);
        userRepo = Mockito.mock(IUserRepo.class);

        bookingService = new BookingServiceOptimistic(
                ticketRepo,
                ticketTypeRepo,
                transactionRepo,
                userRepo,
                (EntityManager) BookingApplication.getAppContext()
        );

        User bookingUser = new User("Minh");
        bookingUser.setId(1);
        Mockito.when(userRepo.getUserById(1)).thenReturn(bookingUser);

        TicketType ticketType = new TicketType(TicketService.NORMAL_TYPE, 10);
        Mockito.when(ticketTypeRepo.getTicketTypeByType(TicketService.NORMAL_TYPE)).thenReturn(ticketType);

        List<Ticket> availableTickets = new ArrayList<>();
        for (int i = 0; i < ticketType.getAmount(); i++) {
            Ticket newTicket = new Ticket(ticketType);
            newTicket.setId(i + 1);
            availableTickets.add(newTicket);
        }

        Mockito.when(ticketRepo.getTicketsByTypeAndIsAvailable(ticketType, true)).thenReturn(availableTickets);
        Mockito.when(userRepo.getUserById(1)).thenReturn(bookingUser);
    }

    @Test
    public void testUserBookingTicketWithType() {

        // Thực hiện phương thức để kiểm tra
        bookingService.userBookingTicketWithType(1, TicketService.NORMAL_TYPE);

        // Đảm bảo rằng các phương thức đã được gọi
        Mockito.verify(userRepo).getUserById(1);
        Mockito.verify(transactionRepo).save(Mockito.any(Transaction.class));
    }

    // Tương tự, bạn có thể viết các unit tests cho các phương thức khác trong lớp BookingService
}
