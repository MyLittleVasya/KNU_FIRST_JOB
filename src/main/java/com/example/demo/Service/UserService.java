package com.example.demo.Service;

import com.example.demo.Entity.Feature;
import com.example.demo.Entity.Profile;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repo.FeatureRepo;
import com.example.demo.Repo.ProfileRepo;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FeatureRepo featureRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        user.setUserRole(Collections.singleton(UserRole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        var profile = new Profile();
        profileRepo.save(profile);
        user.setProfile(profile);
        userRepo.save(user);
        return true;
    }

    public void addFeatures(String features, String id)
    {
        String[] skills = features.split(",");
        var user = userRepo.findById(Long.parseLong(id));
        user.getFeatures().clear();
        for (String skill: skills)
        {
            if (!StringUtils.isEmpty(skill))
            {
                if (featureRepo.findByName(skill.toUpperCase()) == null)
                {
                    var newFeature = new Feature(skill.toUpperCase(Locale.ROOT));
                    user.getFeatures().add(newFeature);
                }
                else
                {
                    var newFeature = featureRepo.findByName(skill.toUpperCase(Locale.ROOT));
                    user.getFeatures().add(newFeature);
                }
            }
        }
        userRepo.save(user);
    }

    public void updateProfile(Map<String, String> body) {
        var user = userRepo.findById(Long.parseLong(body.get("user_id")));
        if (!StringUtils.isEmpty(body.get("university")))
            user.getProfile().setUniversity(body.get("university"));
        if (!StringUtils.isEmpty(body.get("spec")))
            user.getProfile().setSpecialization(body.get("spec"));
        if (!StringUtils.isEmpty(body.get("exp")))
            user.getProfile().setExperience(Double.parseDouble(body.get("exp")));
        if (!StringUtils.isEmpty(body.get("formOfWork")))
            user.getProfile().setFormOfWork(body.get("formOfWork"));
        if (!StringUtils.isEmpty(body.get("story")))
            user.getProfile().setStory(body.get("story"));
        if (!StringUtils.isEmpty(body.get("marks")))
            user.getProfile().setMarks(body.get("marks"));
        if (!StringUtils.isEmpty(body.get("contact")))
            user.getProfile().setCommunication(body.get("contact"));
        if (!StringUtils.isEmpty(body.get("pet")))
            user.getProfile().setPetProject(body.get("pet"));
        if (!StringUtils.isEmpty(body.get("cv")))
            user.getProfile().setCv(body.get("cv"));
        userRepo.save(user);
    }
}
