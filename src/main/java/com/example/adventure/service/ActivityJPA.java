package com.example.adventure.service;

import com.example.adventure.model.Activity;
import com.example.adventure.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ActivityJPA implements ActivityService{

    private ActivityRepository activityRepository;

    public ActivityJPA(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Set<Activity> findAll() {
        Set<Activity> activities = new HashSet<>();
        activityRepository.findAll().forEach(activities::add);
        return activities;
    }

    @Override
    public Activity save(Activity object) {
        return activityRepository.save(object);
    }

    @Override
    public void delete(Activity object) {
        activityRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        activityRepository.deleteById(aLong);

    }

    @Override
    public Optional<Activity> findById(Long aLong) {
        return activityRepository.findById(aLong);
    }
}
