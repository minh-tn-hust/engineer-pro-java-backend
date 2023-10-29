package com.engineerpro.booking.repositories.user;

import com.engineerpro.booking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    User getUserById(Integer id);
}
