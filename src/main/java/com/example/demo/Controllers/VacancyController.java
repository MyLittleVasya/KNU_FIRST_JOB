package com.example.demo.Controllers;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Vacancy;
import com.example.demo.Repo.FeatureRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Repo.VacancyRepo;
import com.example.demo.Service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class VacancyController {

    @Autowired
    private FeatureRepo featureRepo;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyRepo vacancyRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/createVacancy")
    public String getVacancyForm(Model model){
        return "vacancyForm";
    }
    @PostMapping("/createVacancy")
    public String createVacancy(@RequestParam Map<String, String> body) {
        vacancyService.createVacancy(body);
        return "redirect:/";
    }

    @GetMapping("/vacancy/{id}")
    public String getVacancy(@PathVariable long id, Model model, @AuthenticationPrincipal User user)
    {
        var vacancy = vacancyRepo.findById(id);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("visitor", user);
        return "vacancy";
    }

    @GetMapping("/vacancyList")
    public String getVacancyList(Model model, @AuthenticationPrincipal User user)
    {
        user = userRepo.findById(user.getId());
        var vacancies = vacancyRepo.findAll();
        var result = new HashMap<Double, Vacancy>();
        model.addAttribute("vacancies", vacancyService.formVacancyRating(user, vacancies, result));
        return "vacancyList";
    }

    @PostMapping("/apply")
    public String applyForJob(@RequestParam Map<String, String> body, @AuthenticationPrincipal User user)
    {
        var vacancy = vacancyRepo.findById(Long.parseLong(body.get("vacancy")));
        if (!vacancy.getCandidates().contains(user))
            vacancy.getCandidates().add(user);
        vacancyRepo.save(vacancy);
        return "redirect:/vacancyList";
    }

    @GetMapping("/viewAuthorVacancies")
    public String getVacancies(Model model, @AuthenticationPrincipal User user)
    {
        model.addAttribute("vacancies", vacancyRepo.findByAuthor(user));
        return "authorVacancyList";
    }

    @GetMapping("/candidates/{vacancyId}")
    public String getCandidates(@PathVariable long vacancyId, Model model)
    {
        var vacancy = vacancyRepo.findById(vacancyId);
        model.addAttribute("users", vacancyService.createUserRating(vacancy));
        return "candidatesList";
    }

}
