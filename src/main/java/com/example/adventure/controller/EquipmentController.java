package com.example.adventure.controller;

import com.example.adventure.exception.ResourceNotFoundException;
import com.example.adventure.model.Equipment;
import com.example.adventure.service.EquipmentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class EquipmentController {
/*    private EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService){
        this.equipmentService = equipmentService;
    }

@GetMapping("/equipment")
    public ResponseEntity<Set<Equipment>>getEquipment(){
        return new ResponseEntity<>(equipmentService.findAll(), HttpStatus.OK);
}
@PostMapping("/addequipment")
    public ResponseEntity<Set<Equipment>>addEquipment(Equipment name){
        equipmentService.save(name);
        return new ResponseEntity<>(equipmentService.findAll(), HttpStatus.OK);
}
@DeleteMapping("/deleteequipment")
    public ResponseEntity<Set<Equipment>>deleteEquipmentById(Equipment id){
        equipmentService.delete(id);
        return new ResponseEntity<>(equipmentService.findAll(), HttpStatus.OK);
}
@PutMapping("/updateequipment")
    public ResponseEntity<Set<Equipment>>updateEquipment(Long id, Equipment name){
        Equipment equipmentUpdate = equipmentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("No one exists with this id: " + id));
        equipmentUpdate.setName(name.getName());
        equipmentService.save(equipmentUpdate);
        return new ResponseEntity<>(equipmentService.findAll(), HttpStatus.OK);
    }*/
}
