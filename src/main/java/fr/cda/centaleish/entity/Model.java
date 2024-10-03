
package fr.cda.centaleish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.ModelMinimalView.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.ModelMinimalView.class)
    private String name;

    @ManyToOne
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private List<Listing> listings = new ArrayList<>();

    @JsonView(JsonViews.ModelMinimalView.class)
    private Integer listingCount() {
        return listings.size();
    }

}
