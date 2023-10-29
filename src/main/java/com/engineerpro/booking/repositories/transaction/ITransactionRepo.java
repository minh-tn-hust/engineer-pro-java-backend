package com.engineerpro.booking.repositories.transaction;

import com.engineerpro.booking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepo extends JpaRepository<Transaction, Integer> {
    Transaction getTransactionById(Integer id);
}
