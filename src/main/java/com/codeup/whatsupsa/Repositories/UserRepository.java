package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
