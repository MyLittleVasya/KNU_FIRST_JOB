package com.example.demo.Service;

import com.example.demo.Entity.Feature;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Vacancy;
import com.example.demo.Repo.FeatureRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Repo.VacancyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class VacancyService {

    @Autowired
    private FeatureRepo featureRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VacancyRepo vacancyRepo;
    public void createVacancy(Map<String, String> body) {
        String[] skills = body.get("features").split(",");
        var user = userRepo.findById(Long.parseLong(body.get("author_id")));
        var vacancy = new Vacancy(user, body.get("name"), body.get("story"), Double.parseDouble(body.get("exp")), Integer.parseInt(body.get("salary")));
        for (String skill: skills)
        {
            if (!StringUtils.isEmpty(skill))
            {
                if (featureRepo.findByName(skill.toUpperCase()) == null)
                {
                    featureRepo.save(new Feature(skill.toUpperCase(Locale.ROOT)));
                }
            }
        }
        for (String skill: skills)
        {
            if (!StringUtils.isEmpty(skill))
            {
                vacancy.getFeatures().add(featureRepo.findByName(skill.toUpperCase(Locale.ROOT)));
            }
        }
        vacancyRepo.save(vacancy);
    }

    public Map<Vacancy, Double> formVacancyRating(User user, List<Vacancy> vacancies, Map<Double, Vacancy> result)
    {
        for (var vacancy : vacancies)
        {
            double userRating = 0;
            double skills = 0;
            double maxRating = 10+(vacancy.getFeatures().size()*4);
            for (var feature : user.getFeatures())
            {
                if (vacancy.getFeatures().contains(feature))
                {
                    userRating+=4;
                    skills++;
                }

            }
            userRating += user.getProfile().getExperience()*10/vacancy.getExperience();
            userRating = userRating*100/maxRating;
            if (skills == 0)
                userRating = 0;
            if (userRating > 100)
                userRating = 100;
            userRating += new Random().nextDouble();
            result.put(userRating, vacancy);
        }
        var listOfValues = new ArrayList<Double>(result.keySet());
        Collections.sort(listOfValues, Collections.reverseOrder());
        var newResult = new LinkedHashMap<Vacancy, Double>();
        for (var value:listOfValues) {
            newResult.put(result.get(value), value);
        }
        return newResult;
    }

    public Map<User, Double> createUserRating(Vacancy vacancy) {
        var result = new HashMap<Double, User>();
        for (var user : vacancy.getCandidates())
        {
            double userRating = 0;
            double skills = 0;
            double maxRating = 10+(vacancy.getFeatures().size()*4);
            for (var feature : user.getFeatures())
            {
                if (vacancy.getFeatures().contains(feature))
                {
                    userRating+=4;
                    skills++;
                }

            }
            userRating += user.getProfile().getExperience()*10/vacancy.getExperience();
            userRating = userRating*100/maxRating;
            if (skills == 0)
                userRating = 0;
            if (userRating > 100)
                userRating = 100;
            userRating += new Random().nextDouble();
            result.put(userRating, user);
        }
        var listOfValues = new ArrayList<Double>(result.keySet());
        Collections.sort(listOfValues, Collections.reverseOrder());
        var newResult = new LinkedHashMap<User, Double>();
        for (var value:listOfValues) {
            newResult.put(result.get(value), value);
        }
        return newResult;
    }
}
