package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.Event;
import com.codeup.whatsupsa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventsRepository extends JpaRepository<Event, Long> {

    Event findByTitle(String title);

    @Query("from Event e where e.user.id = ?1")
    List<Event> FindEventsByUserID(Long id);

}
