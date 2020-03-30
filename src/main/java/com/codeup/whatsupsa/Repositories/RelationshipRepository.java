package com.codeup.whatsupsa.Repositories;

import com.codeup.whatsupsa.models.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
}
