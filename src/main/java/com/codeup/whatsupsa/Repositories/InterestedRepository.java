package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.Event;
import com.codeup.whatsupsa.models.Interested;
import com.codeup.whatsupsa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestedRepository extends JpaRepository<Interested, Long> {

    @Query("FROM Interested a WHERE a.userID = ?1 AND a.event = ?2")
     Interested checkIfInterested(User user, Event event);

}
