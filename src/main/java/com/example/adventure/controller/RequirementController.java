package com.example.adventure.controller;


import com.example.adventure.model.Requirement;
import com.example.adventure.service.RequirementJPA;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/requirements")
public class RequirementController {

    private final RequirementJPA reqJPA;

    public RequirementController(RequirementJPA reqJPA) {
        this.reqJPA = reqJPA;
    }

    @GetMapping
    public ResponseEntity<Set<Requirement>> getAllReqs(){
        return new ResponseEntity<>(reqJPA.findAll(), HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Set<Requirement>> getRequirementsById(@PathVariable long id){
        Optional<Requirement> currentReqs = reqJPA.findById(id);
        if(currentReqs.isPresent()){
            return new ResponseEntity<>(reqJPA.findAll(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(reqJPA.findAll(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Requirement> addCourse (Requirement newReqs){
        return new ResponseEntity<>(reqJPA.save(newReqs),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Set<Requirement>> deleteReqs (@PathVariable Requirement requirement){
        reqJPA.delete(requirement);
        return new ResponseEntity<>(reqJPA.findAll(),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Requirement> editReqs(@PathVariable Requirement requirement){

        Optional<Requirement> reqTemp = reqJPA.findById(requirement.getId());

        if(reqTemp.isPresent()){
            if(requirement.getId() == null) {
                requirement.setId(requirement.getId());
            }
            if(requirement.getMinimumAttendants() == 0) {
                requirement.setMinimumAttendants(reqTemp.get().minimumAttendants);
            }
            if(requirement.getMaximumAttendants() == 0) {
                requirement.setMaximumAttendants(reqTemp.get().maximumAttendants);
            }
            if(requirement.getAlcoholLevel() == 0) {
                requirement.setAlcoholLevel(reqTemp.get().alcoholLevel);
            }
            if(requirement.getMaxWeight() == 0){
                requirement.setMaxWeight(reqTemp.get().maxWeight);
            }
            if(requirement.getMinimumHeight() == 0) {
                requirement.setMinimumHeight(reqTemp.get().minimumHeight);
            }
            if(requirement.getMaximumHeight() == 0) {
                requirement.setMaximumHeight(reqTemp.get().maximumHeight);
            }
            if(requirement.getMinimumAge() == 0) {
                requirement.setMinimumAge(reqTemp.get().minimumAge);
            }
            if(requirement.getMaximumAge() == 0) {
                requirement.setMaximumAge(reqTemp.get().maximumAge);
            }
            if(requirement.getRequirementsDescrip() == null) {
                requirement.setRequirementsDescrip(reqTemp.get().requirementsDescrip);
            }
            reqJPA.save(requirement);
            System.out.println(requirement.alcoholLevel);
            return new ResponseEntity<>(requirement,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(requirement,HttpStatus.NOT_FOUND);
        }
    }

}
