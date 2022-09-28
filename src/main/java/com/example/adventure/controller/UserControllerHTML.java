package com.example.adventure.controller;

import com.example.adventure.model.User;
import com.example.adventure.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserControllerHTML {

    private UserService uService;

    public UserControllerHTML(UserService uService){
        this.uService = uService;
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/add-user";
    }


    @PostMapping("/save-user")
    public String addUser(@ModelAttribute("user") User user, Model model){
       uService.save(user);
        model.addAttribute("user", user);
            return "redirect:/user-frontpage";
        }
    @GetMapping("/user-frontpage")
    public String showUserList(Model model) {
        Set<User> users = new HashSet<>(uService.findAll());
        model.addAttribute("users", users);
        return "user-frontpage";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
       User user = uService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            model.addAttribute("user", user);
            return "update-user";
        }
        uService.save(user);
        return "redirect:/user-frontpage";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = uService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        uService.delete(user);
        return "redirect:/user-frontpage";
    }

}

