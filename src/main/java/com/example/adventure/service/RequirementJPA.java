package com.example.adventure.service;

import com.example.adventure.model.Requirement;
import com.example.adventure.repository.RequirementRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RequirementJPA implements RequirementService {

    public final RequirementRepository requirementsRepository;

    public RequirementJPA(RequirementRepository requirementsRepository) {
        this.requirementsRepository = requirementsRepository;
    }

    @Override
    public Set<Requirement> findAll() {
        Set<Requirement> set = new HashSet<>();
        requirementsRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Requirement save(Requirement object) {
        return requirementsRepository.save(object);
    }

    @Override
    public void delete(Requirement object) {
        requirementsRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        requirementsRepository.deleteById(aLong);
    }

    @Override
    public Optional<Requirement> findById(Long aLong) {
        return requirementsRepository.findById(aLong);
    }
}
