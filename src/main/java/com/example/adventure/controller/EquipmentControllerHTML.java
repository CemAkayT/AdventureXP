package com.example.adventure.controller;

import com.example.adventure.model.Equipment;
import com.example.adventure.model.User;
import com.example.adventure.service.EquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class EquipmentControllerHTML {
    private final EquipmentService eService;

    public EquipmentControllerHTML(EquipmentService eService) {
        this.eService = eService;
    }
    @GetMapping("/add-equipment")
    public String addEquipment(Model model) {
        Equipment equipment = new Equipment();
        model.addAttribute("equipment", equipment);
        return "/add-equipment";
    }


    @PostMapping("/save-equipment")
    public String addEquipment(@ModelAttribute("equipment") Equipment equipment, Model model){
        eService.save(equipment);
        model.addAttribute("equipment", equipment);
        return "redirect:/equipment-frontpage";
    }
    @GetMapping("/equipment-frontpage")
    public String showEquipmentList(Model model) {
        Set<Equipment> equipment = new HashSet<>(eService.findAll());
        model.addAttribute("equipment", equipment);
        return "equipment-frontpage";
    }
    @GetMapping("/editEquipment/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Equipment equipment = eService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid equipment Id:" + id));

        model.addAttribute("equipment", equipment);
        return "update-equipment";
    }
    @PostMapping("/updateEquipment/{id}")
    public String updateEquipment(@PathVariable("id") long id, @ModelAttribute("equipment") Equipment equipment,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            equipment.setId(id);
            model.addAttribute("equipment", equipment);
            return "update-equipment";
        }
        eService.save(equipment);
        return "redirect:/equipment-frontpage";
    }

    @GetMapping("/deleteEquipment/{id}")
    public String deleteEquipment(@PathVariable("id") long id, Model model) {
        Equipment equipment = eService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        eService.delete(equipment);
        return "redirect:/equipment-frontpage";
    }
}
