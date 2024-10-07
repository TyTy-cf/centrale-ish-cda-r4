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
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.BrandMinimalView.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.BrandMinimalView.class)
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonView(JsonViews.BrandShowView.class)
    private List<Model> models = new ArrayList<>();

    @JsonView(JsonViews.BrandListView.class)
    private Integer modelCount() {
        return models.size();
    }

}
