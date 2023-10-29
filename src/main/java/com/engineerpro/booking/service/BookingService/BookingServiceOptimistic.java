package com.engineerpro.booking.service.BookingService;

import com.engineerpro.booking.models.Ticket;
import jakarta.persistence.LockModeType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("bookingServiceOptimistic")
public class BookingServiceOptimistic extends BookingService{
    @Override
    public void processBookingTicket(Ticket ticket) {
        entityManager.lock(ticket, LockModeType.OPTIMISTIC);
        ticket.setIsAvailable(false);
        entityManager.merge(ticket);
    }
}
