package com.example.adventure.controller;

import com.example.adventure.model.DateDto;
import com.example.adventure.model.Shift;
import com.example.adventure.model.ShiftAndActivityRequest;
import com.example.adventure.model.ShiftAndActivityResponse;
import com.example.adventure.service.ActivityService;
import com.example.adventure.service.ShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/shift")
public class ShiftController {

    private ShiftService shiftService;
    private ActivityService activityService;


    public ShiftController(ShiftService shiftService, ActivityService activityService) {
        this.shiftService = shiftService;
        this.activityService = activityService;
    }

    @PermitAll
    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> allShifts(){
        return new ResponseEntity<>(shiftService.findAll(), HttpStatus.OK);
    }

    @PermitAll
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getShiftById(@PathVariable Long id){
        //Optional<Shift> shift = shiftService.findById(id);
        return new ResponseEntity<>(shiftService.findById(id), HttpStatus.OK);
    }

    @PermitAll
    @PostMapping
    @CrossOrigin
    public ResponseEntity<?> saveShift(@RequestBody ShiftAndActivityRequest shiftAndActivityRequest){
            Shift shift = new Shift();
            shift.setEmployeeName(shiftAndActivityRequest.getEmployeeName());
            shift.setStartTime(shiftAndActivityRequest.getStartTime());
            shift.setEndTime(shiftAndActivityRequest.getEndTime());
            shift.setActivityDescription(shiftAndActivityRequest.getActivityDescription());
            Shift savedShift = shiftService.save(shift);
        if (savedShift != null) {
            return new ResponseEntity<>(savedShift, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Shift(), HttpStatus.BAD_REQUEST);
    }

    @PermitAll
    @PutMapping()
    @CrossOrigin
    public ResponseEntity<?> updateShift(@RequestBody Shift shift){
        shiftService.save(shift);
        return new ResponseEntity<>(new Shift(), HttpStatus.OK);
    }

    @PermitAll
    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteShift(@PathVariable Long id){
        Optional<Shift> shiftToDelete = shiftService.findById(id);
        if (shiftToDelete.isPresent()){
            shiftService.deleteById(id);
            return new ResponseEntity<>(new Shift(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Shift(), HttpStatus.BAD_REQUEST);
    }

    @PermitAll
    @PostMapping("/get-by-date")
    @CrossOrigin
    public ResponseEntity<?> getActivityByDate(@RequestBody DateDto dateDto){
        LocalDateTime localDateStart = dateDto.getDate().atTime(00,1, 1, 000000);
        System.out.println(localDateStart);
        LocalDateTime localDateEnd = dateDto.getDate().atTime(23,59, 59, 000000);
        System.out.println(localDateEnd);
        Set<Shift> shifts = shiftService.getActivityByDate(localDateStart, localDateEnd);
        List<ShiftAndActivityResponse> ShiftAndActivityResponses = new ArrayList<>();
        shifts.forEach(shift -> {
            ShiftAndActivityResponse shiftAndActivityResponse = new ShiftAndActivityResponse();


            shiftAndActivityResponse.setShiftId(shift.getId());
            shiftAndActivityResponse.setEmployeeName(shift.getEmployeeName());

            LocalDateTime startDateTime = shift.getStartTime();
            String startTime = startDateTime.getHour() + ":" + startDateTime.getMinute();
            shiftAndActivityResponse.setStartTimeString(startTime);

            LocalDateTime endDateTime = shift.getEndTime();
            String endTime = endDateTime.getHour() + ":" + endDateTime.getMinute();
            shiftAndActivityResponse.setEndTimeString(endTime);

            shiftAndActivityResponse.setActivityDescription(shift.getActivityDescription());

            ShiftAndActivityResponses.add(shiftAndActivityResponse);
        });
        return new ResponseEntity<>(ShiftAndActivityResponses, HttpStatus.OK);
    }

}
