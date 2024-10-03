package fr.cda.centaleish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViews.UserMinimalView.class)
    private String uuid;

    @Column(nullable = false)
    @JsonView(JsonViews.UserMinimalView.class)
    private String email;

    @JsonView(JsonViews.UserShowView.class)
    private String firstName;

    @JsonView(JsonViews.UserShowView.class)
    private String lastName;

    @JsonView(JsonViews.UserShowView.class)
    private String phone;

    @JsonView(JsonViews.UserShowView.class)
    private String siret;

    @Column(nullable = false)
    private String password;

    @JsonView(JsonViews.UserShowView.class)
    private String photo;

    private String activationCode;

    private LocalDateTime activationCodeSentAt;

    @JsonView(JsonViews.UserShowView.class)
    private LocalDate birthAt;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShowView.class)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String roles;

    @OneToOne(mappedBy = "user")
    private Address address;

    @OneToMany(mappedBy = "owner")
    private List<Listing> listings = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserShowView.class)
    private List<Favorite> favorites = new ArrayList<>();

    @JsonView(JsonViews.UserMinimalView.class)
    private Boolean getIsActive() {
        return activationCode == null;
    }

    @JsonView(JsonViews.UserMinimalView.class)
    private Boolean getIsAdmin() {
        return roles.contains("ROLE_ADMIN");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = Collections.singletonList(this.roles);
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }
}