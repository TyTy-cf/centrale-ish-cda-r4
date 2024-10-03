
package fr.cda.centaleish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViews.ListingListView.class)
    private String uuid;

    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonView(JsonViews.ListingListView.class)
    private String path;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Listing listing;

}
