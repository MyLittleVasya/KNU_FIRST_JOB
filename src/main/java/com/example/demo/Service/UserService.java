package com.example.demo.Service;

import com.example.demo.Entity.Feature;
import com.example.demo.Entity.Profile;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRole;
import com.example.demo.Repo.FeatureRepo;
import com.example.demo.Repo.ProfileRepo;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user, Map<String, String> body) {
        user.setUserRole(Collections.singleton(UserRole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false);
        var profile = new Profile();
        profileRepo.save(profile);
        user.setProfile(profile);
        user.getProfile().setEmail(body.get("email"));
        user.setActivationCode(UUID.randomUUID().toString());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(user.getProfile().getEmail());
        message.setSubject("Activate your account please. No Reply");
        message.setText(String.format(
                "Hello, %s! \n" +
                        "Welcome to FirstJob! \n"+
                        "Visit next link to activate your account \n"+
                        "https://knu-first-job-project.herokuapp.com/activate/%s",
                user.getUsername(),
                user.getActivationCode()));
        emailSender.send(message);
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
        if (!StringUtils.isEmpty(body.get("pib")))
            user.getProfile().setPIB(body.get("pib"));
        userRepo.save(user);
    }

    public String changeSettings(User user, Map<String, String> body) {
        user = userRepo.findById(user.getId());
        if (user.getProfile().getEmail().equals(body.get("email")) || profileRepo.findByEmail(body.get("email")) != null)
        {
            if (!StringUtils.isEmpty(body.get("password")) && !passwordEncoder.encode(body.get("password")).equals(user.getPassword()))
            {
                SimpleMailMessage message_p = new SimpleMailMessage();
                message_p.setFrom(username);
                message_p.setTo(user.getProfile().getEmail());
                message_p.setSubject("Password was changed. No Reply");
                message_p.setText(String.format(
                        "Hello, %s! \n" +
                                "We have to notify you, that your account password was changed. \n"+
                                "Visit next link to confirm these changes: \n"+
                                "http://localhost:8080/changePassword/%s/%s \n"+
                                "If you didn't change your password, you should delete this message!",
                        user.getUsername(),
                        body.get("password"),
                        user.getActivationCode()));
                emailSender.send(message_p);
                return "changes";
            }
            return "stable";
        }
        else
        {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo(user.getProfile().getEmail());
            message.setSubject("Email was changed. No Reply");
            message.setText(String.format(
                    "Hello, %s! \n" +
                            "We have to notify you, that your account email was changed. \n"+
                            "Your new email should be %s.\n"+
                            "Visit next link to confirm these changes: \n"+
                            "http://localhost:8080/changeEmail/%s/%s \n"+
                            "If you didn't change your email, you should delete this message!",
                    user.getUsername(),
                    body.get("email"),
                    body.get("email"),
                    user.getActivationCode()));
            emailSender.send(message);
            if (!StringUtils.isEmpty(body.get("password")) && !passwordEncoder.encode(body.get("password")).equals(user.getPassword()))
            {
                SimpleMailMessage message_p = new SimpleMailMessage();
                message_p.setFrom(username);
                message_p.setTo(user.getProfile().getEmail());
                message_p.setSubject("Password was changed. No Reply");
                message_p.setText(String.format(
                        "Hello, %s! \n" +
                                "We have to notify you, that your account password was changed. \n"+
                                "Visit next link to confirm these changes: \n"+
                                "http://localhost:8080/changePassword/%s/%s \n"+
                                "If you didn't change your password, you should delete this message!",
                        user.getUsername(),
                        body.get("password"),
                        user.getActivationCode()));
                emailSender.send(message_p);
            }
            return "changes";
        }
    }

    public void changeEmail(User user, String email) {
        user.getProfile().setEmail(email);
    }


    public void changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
    }
}
