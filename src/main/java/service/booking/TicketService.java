package service.booking;

import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.repositories.ticket.ITicketTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private ITicketRepo ticketRepo;

    @Autowired
    private ITicketTypeRepo ticketTypeRepo;

    void addTicketType(Integer amount, String type) {

    }
}
