package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/test")
    public String test()
    {
        return "main";
    }
    @GetMapping("/")
    public String test2()
    {
        return "index";
    }
}
