package service.booking;

import com.engineerpro.booking.models.Transaction;
import com.engineerpro.booking.repositories.transaction.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private ITransactionRepo transactionRepo;
    public void userBookingRoom(Integer userId, Integer roomId) {

    }

    public void userCancelRoom(Integer userId, Integer roomId) {

    }
}
