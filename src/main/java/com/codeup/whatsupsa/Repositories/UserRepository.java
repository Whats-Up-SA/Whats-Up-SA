package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User getUserById(Long Id);

}
