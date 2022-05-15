package com.example.demo.Controllers;

import com.example.demo.Entity.User;
import com.example.demo.Repo.ProfileRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileRepo profileRepo;

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
        if (profileRepo.findByEmail(body.get("email")) != null)
        {
            model.addAttribute("message", "this email is already used");
            return "registration";
        }
        if (userService.addUser(user, body))
        {
            return "redirect:/login";
        }
        else
        {
            model.addAttribute("message", "something went wrong");
            return "registration";
        }
    }

    @GetMapping("/activate/{uuid}")
    public String activateAccount(@PathVariable String uuid)
    {
        var user = userRepo.findByActivationCode(uuid);
        if (user != null)
        {
            user.setActive(true);
        }
        userRepo.save(user);
        if (user.isActive())
        {
            return "index";
        }
        return "activatedAccount";
    }

    @GetMapping("/settings")
    public String getAccountSettings(@AuthenticationPrincipal User user, Model model)
    {
        user = userRepo.findById(user.getId());
        model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping("/settings")
    public String changeAccountSettings(@AuthenticationPrincipal User user, Model model, @RequestParam Map<String, String> body)
    {
        user = userRepo.findById(user.getId());
        model.addAttribute("user", user);
        if (userService.changeSettings(user, body).equals("stable"))
        {
            model.addAttribute("message", "Nothing have been changed");
        }
        else
        {
            model.addAttribute("message", "Account settings were changed\nCheck your email to confirm them");
        }
        return "settings";
    }

    @GetMapping("/changeEmail/{email}/{uuid}")
    public String changeEmail(@PathVariable String uuid, @PathVariable String email, Model model)
    {
        var user = userRepo.findByActivationCode(uuid);
        userService.changeEmail(user, email);
        userRepo.save(user);
        model.addAttribute("message", "Email was successfully changed");
        return "index";
    }
    @GetMapping("/changePassword/{password}/{uuid}")
    public String changePassword(@PathVariable String uuid, @PathVariable String password, Model model)
    {
        var user = userRepo.findByActivationCode(uuid);
        userService.changePassword(user, password);
        userRepo.save(user);
        model.addAttribute("message", "Email was successfully changed");
        return "index";
    }

}
