package com.example.demo.Service;

import com.example.demo.Entity.Feature;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repo.FeatureRepo;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Locale;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FeatureRepo featureRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        user.setUserRole(Collections.singleton(UserRole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
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
}
