package com.vitkulov.tests.Test_2.controller;

import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;

@Controller
@RequestMapping(path = "/user")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/create")
    public @ResponseBody
    String addNewUser(@RequestBody User user) {
        user.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
