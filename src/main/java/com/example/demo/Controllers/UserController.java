package com.example.demo.Controllers;

import com.example.demo.Entity.User;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model)
    {
       return "registration";
    }

    @PostMapping("/registration")
    public String Registration(Model model, @RequestParam Map<String, String> body)
    {
        var user = new User();
        user.setUsername(body.get("username"));
        user.setPassword(body.get("password"));
        if (userRepo.findByUsername(user.getUsername()) != null || user.getUsername().isEmpty())
        {
            model.addAttribute("message", "username is not valid or already exist");
            return "registration";
        }
        if (userService.addUser(user))
        {
            return "redirect:/login";
        }
        else
        {
            model.addAttribute("message", "something went wrong");
            return "registration";
        }
    }

}
