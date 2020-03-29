package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Long> {

    Events findByTitle(String title);

}
