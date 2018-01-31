package com.vitkulov.tests.Test_2.controller;

import com.vitkulov.tests.Test_2.dto.FilterFormDto;
import com.vitkulov.tests.Test_2.dto.PageWrapper;
import com.vitkulov.tests.Test_2.dto.UserDto;
import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.service.RecordService;
import com.vitkulov.tests.Test_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    private final UserService userService;
    private final RecordService recordService;

    @Autowired
    public MainController(UserService userService, RecordService recordService) {
        this.userService = userService;
        this.recordService = recordService;
    }

    @GetMapping("/")
    public String getAll(Model model, Pageable pageable) {
        Page<User> userPage = userService.getAllUsers(pageable);
        PageWrapper<User> page = new PageWrapper<>(userPage, "/");

        //fixme: //пока для примера вывод на главной странице таблицы с общими суммами
        List<User> pageContent = page.getContent();
        List<UserDto> users = userService.getSumRecords(pageContent);

        model.addAttribute("users", users);
        model.addAttribute("page", page);
        return "views/index";
    }

    @GetMapping("/user/{id}")
    public String getUserInfo(Model model, @PathVariable(name = "id") String id) {
        Long userID = Long.parseLong(id);
        User user = userService.findOneById(userID);
        model.addAttribute("user", user);
        FilterFormDto filterFormDto = new FilterFormDto();
        model.addAttribute("filterFormDto", filterFormDto);

        List<Record> recordList = recordService.findRecordsByCriteria(userID, filterFormDto);
        model.addAttribute("recordList", recordList);
        return "views/info";
    }

    @PostMapping("/user/{id}")
    public String getUserInfo(Model model, @PathVariable(name = "id") String id, @ModelAttribute FilterFormDto filterFormDto) {
        Long userID = Long.parseLong(id);
        User user = userService.findOneById(userID);
        model.addAttribute("user", user);

        List<Record> recordList = recordService.findRecordsByCriteria(userID, filterFormDto);
        model.addAttribute("recordList", recordList);
        return "views/info";
    }

    @PostMapping("/user/new")
    public String addUser(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/new";
        }
        userService.saveNewUser(userDto);
        return "redirect:/";
    }

    @GetMapping("/user/new")
    public String newUser(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "views/new";
    }

    @PostMapping("/user/edit")
    public String updateUser(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "views/edit";
        }
        userService.updateUser(userDto);
        return "redirect:/user/" + userDto.getId();
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(Model model, @PathVariable(name = "id") String id) {
        Long userID = Long.parseLong(id);
        User user = userService.findOneById(userID);
        model.addAttribute("userDto", user);
        return "views/edit";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") String id) {
        Long userID = Long.parseLong(id);
        userService.deleteUserById(userID);
        return "redirect:/";
    }
}
