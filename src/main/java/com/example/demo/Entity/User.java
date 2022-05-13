package com.example.demo.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "user_list")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private boolean active;
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> userRole;

    @OneToOne(fetch = FetchType.EAGER)
    private Profile profile;

    @ManyToMany(cascade=CascadeType.ALL)
    @CollectionTable(name = "user_features", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Feature> features;

    public boolean isAdmin()
    {
        if (userRole == null)
            return false;
        else
            return (userRole.contains(UserRole.ADMIN));
    }

    public double getRating(Vacancy vacancy)
    {
        double userRating = 0;
        double skills = 0;
        double maxRating = 10+(vacancy.getFeatures().size()*4);
        for (var feature : this.getFeatures())
        {
            if (vacancy.getFeatures().contains(feature))
            {
                userRating+=4;
                skills++;
            }

        }
        userRating += this.getProfile().getExperience()*10/vacancy.getExperience();
        userRating = userRating*100/maxRating;
        if (skills == 0)
            userRating = 0;
        if (userRating > 100)
            userRating = 100;
        return userRating;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
