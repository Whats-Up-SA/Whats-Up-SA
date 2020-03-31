package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.Category;
import com.codeup.whatsupsa.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
