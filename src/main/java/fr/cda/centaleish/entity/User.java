package fr.cda.centaleish.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column
    private String siret;

    @Column(nullable = false)
    private String password;

    private String photo;

    @Column
    private String activationCode;

    @Column(nullable = false)
    private LocalDate birthAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String roles;

    @OneToOne(mappedBy = "user")
    private Address address;

    @OneToMany(mappedBy = "owner")
    private List<Listing> listings = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites = new ArrayList<>();

    private Boolean isActive() {
        return activationCode == null;
    }

}