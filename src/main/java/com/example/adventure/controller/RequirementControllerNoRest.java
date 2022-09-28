package com.example.adventure.controller;

import com.example.adventure.model.Activity;
import com.example.adventure.model.Requirement;
import com.example.adventure.service.ActivityService;
import com.example.adventure.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class RequirementControllerNoRest {

    @Autowired
    RequirementService requirementService;
    @Autowired
    ActivityService activityService;

    @GetMapping("/show-requirement/{activity_id}")
    public String showRequirement(@PathVariable long activity_id, Model model){
        Optional<Activity> activity = activityService.findById(activity_id);
        if (activity.isPresent()) {
           Optional<Requirement> requirements = requirementService.findById(activity.get().getRequirement().getId());
            if (requirements.isPresent()) {
                System.out.print(requirements.get().getAlcoholLevel());
                model.addAttribute("requirements", requirements.get());
                return "/KravListe";
            }
        }
        return "/error";
    }
}
