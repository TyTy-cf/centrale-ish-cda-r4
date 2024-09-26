
package fr.cda.centaleish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViews.UserShowView.class)
    private String uuid;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShowView.class)
    private String title;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShowView.class)
    private Long price;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShowView.class)
    private Long mileage;

    @JsonView(JsonViews.UserShowView.class)
    private LocalDateTime producedAt;

    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Fuel fuel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;

    @OneToMany(mappedBy = "listing")
    private List<Image> images = new ArrayList<>();

}
