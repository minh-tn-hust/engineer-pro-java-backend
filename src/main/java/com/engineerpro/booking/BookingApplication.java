package com.engineerpro.booking;

import com.engineerpro.booking.models.User;
import com.engineerpro.booking.repositories.ticket.ITicketRepo;
import com.engineerpro.booking.service.BookingService.BookingService;
import com.engineerpro.booking.service.BookingService.BookingServiceOptimistic;
import com.engineerpro.booking.service.TicketService;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.engineerpro.booking.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BookingApplication {
    @Getter
    private static ApplicationContext appContext;

    public static void main(String[] args) throws InterruptedException {
        appContext = SpringApplication.run(BookingApplication.class, args);

        UserService userService = appContext.getBean(UserService.class);
        List<String> userNames = Arrays.asList("Minh", "Tran", "Nhat", "Hello", "Hi");
        List<User> user = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            user.add(userService.createUserWithName(userNames.get(i % userNames.size())));
        }

        TicketService ticketService = appContext.getBean(TicketService.class);
        ITicketRepo ticketRepo = appContext.getBean(ITicketRepo.class);

        try {
            ticketService.addTicketType(TicketService.NORMAL_TYPE, 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        BookingService bookingService = appContext.getBean(BookingServiceOptimistic.class);

        List<Thread> allThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bookingService.userBookingTicketWithTicketId(user.get(finalI).getId(), 11);
                        System.out.println("[Success] User " + user.get(finalI).getId() + " book successfully ticket 11");
                    } catch (Exception e) {
                        System.out.println("Fail when user = " + user.get(finalI).getId() + " booking ticket 11");
                    }
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
