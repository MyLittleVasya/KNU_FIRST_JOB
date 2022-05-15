package com.example.demo.Controllers;

import com.example.demo.Entity.Feature;
import com.example.demo.Entity.User;
import com.example.demo.Repo.FeatureRepo;
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
public class ProfileController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FeatureRepo featureRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{id}")
    public String getProfile(Model model, @PathVariable long id, @AuthenticationPrincipal User visitor)
    {
        var user = userRepo.findById(id);
        String result = "";
        if (!user.getFeatures().isEmpty())
        {
            for (Feature feature: user.getFeatures())
            {
                result += feature.getName();
                result+=",";
            }
        }
        model.addAttribute("feature", result);
        model.addAttribute("userProfile", user);
        model.addAttribute("visitor", visitor);
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam Map<String, String> body)
    {
        userService.addFeatures(body.get("features"), body.get("user_id"));
        userService.updateProfile(body);
        return "redirect:/";
    }
}
