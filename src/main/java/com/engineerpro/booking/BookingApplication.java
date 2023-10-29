package com.engineerpro.booking;

import com.engineerpro.booking.models.User;
import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.service.BookingService.BookingService;
import com.engineerpro.booking.service.TicketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.engineerpro.booking.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BookingApplication {
    private static ApplicationContext appContext;

    public static void main(String[] args) throws InterruptedException {
        appContext = SpringApplication.run(BookingApplication.class, args);
        String[] listBean = appContext.getBeanDefinitionNames();
        for (String name : listBean) {
            System.out.println(name);
        }


        UserService userService = appContext.getBean(UserService.class);
        List<String> userNames = Arrays.asList("Minh", "Tran", "Nhat", "Hello", "Hi");
        List<User> user = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            user.add(userService.createUserWithName(userNames.get(i)));
        }

        TicketService ticketService = appContext.getBean(TicketService.class);
        ITicketRepo ticketRepo = appContext.getBean(ITicketRepo.class);

        try {
            ticketService.addTicketType(TicketService.NORMAL_TYPE, 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        BookingService bookingService = appContext.getBean(BookingService.class);

        List<Thread> allThreads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    bookingService.userBookingTicket(user.get(finalI).getId(), TicketService.NORMAL_TYPE);
                }
            });

            t.start();
            allThreads.add(t);
        }

        for (Thread t : allThreads) {
            t.join(1000);
        }
    }
}
