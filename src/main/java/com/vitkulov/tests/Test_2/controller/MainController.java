package com.vitkulov.tests.Test_2.controller;

import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("users", userRepository.findAll());
        User user = new User("Vasya");
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/user/{id}")
    public String getUserInfo(Model model, @PathVariable(name = "id") String id) {
        Long userId = Long.parseLong(id);
        User user = userRepository.findOne(userId);
        model.addAttribute("user", user);
        System.out.println(id);
        return "info";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        System.out.println(user.getName());
        return "redirect:/";
    }

    @GetMapping("/user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute User user) {
        System.out.println(user.getId() + " " + user.getName());
        userRepository.save(user);
        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/user/edit")
    public String editUser(Model model, @ModelAttribute User user) {
        System.out.println(user.getId() + " " + user.getName());
        User result = userRepository.findOne(user.getId());
        model.addAttribute("user", result);
        return "edit";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@ModelAttribute User user) {
        System.out.println(user.getId());
        userRepository.delete(user);
        return "redirect:/";
    }
}
