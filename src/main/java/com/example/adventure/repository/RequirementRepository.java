package com.example.adventure.repository;

import com.example.adventure.model.Requirement;
import org.springframework.data.repository.CrudRepository;

public interface RequirementRepository extends CrudRepository<Requirement, Long> {
}
