package com.engineerpro.booking.service.BookingService;

import com.engineerpro.booking.models.Ticket;
import jakarta.persistence.LockModeType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("bookingServicePessimistic")
public class BookingServicePessimistic extends BookingService{
    @Override
    public void processBookingTicket(Ticket ticket) {
        entityManager.lock(ticket, LockModeType.PESSIMISTIC_WRITE);
        ticket.setIsAvailable(false);
        entityManager.merge(ticket);
    }
}
