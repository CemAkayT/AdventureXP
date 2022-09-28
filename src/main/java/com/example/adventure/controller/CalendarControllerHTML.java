package com.example.adventure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarControllerHTML {
    @GetMapping("/calendar")
    public String home() {
        return "calendar";
    }
}
