package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Long> {

    Event findByTitle(String title);

}
