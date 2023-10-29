package com.engineerpro.booking.service;

import com.engineerpro.booking.models.Ticket;
import com.engineerpro.booking.models.TicketType;
import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.repositories.ticket.ITicketTypeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TicketService {
    public final static List<String> TYPE = Arrays.asList(
            "NORMAL",
            "SEMI_VIP",
            "VIP"
    );

    public final static String NORMAL_TYPE = "NORMAL";
    public final static String SEMI_VIP_TYPE = "SEMI_VIP";
    public final static String VIP_TYPE = "VIP";

    @Autowired
    private ITicketRepo ticketRepo;

    @Autowired
    private ITicketTypeRepo ticketTypeRepo;

    public void addTicketType(String type, Integer amount) throws Exception {
        if (TYPE.contains(type)) {
            TicketType newTicketType = new TicketType(type, amount);
            try {
                ticketTypeRepo.save(newTicketType);

                for (int i = 0; i < amount; i++) {
                    Ticket newTicket = new Ticket(newTicketType);
                    ticketRepo.save(newTicket);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Hạng vé " + type + " không tồn tại trong hệ thống.");
        }
    }
}
