package com.example.adventure.controller;

import com.example.adventure.model.Activity;
import com.example.adventure.service.ActivityJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/activity")
public class ActivityController {
    ActivityJPA activityJPA;

    public ActivityController(ActivityJPA activityJPA) {
        this.activityJPA = activityJPA;
    }

    @PostMapping
    public ResponseEntity<?> saveActivity(@RequestBody Activity activity){
        Activity savedActivity = activityJPA.save(activity);
        if (savedActivity == null){
            return new ResponseEntity<>("Fejl i opretttelse af aktivitet", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(savedActivity, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<Set<Activity>> getAllActivities(){
        Set allActivities = activityJPA.findAll();
        return new ResponseEntity<>(allActivities, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable Activity activity){
        Optional<Activity> activityToUpdate = activityJPA.findById(activity.getId());
        if (activityToUpdate.isPresent()) {
            Activity savedActivity = activityJPA.save(activity);
            if (savedActivity == null) {
                return new ResponseEntity<>("Fejl i opdatering af aktivitet", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(savedActivity, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("Kunne ikke finde aktivitet", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Long id){
        activityJPA.deleteById(id);
        return new ResponseEntity<>("Aktivitet med id " + id + " blev slettet", HttpStatus.OK);
    }

}
