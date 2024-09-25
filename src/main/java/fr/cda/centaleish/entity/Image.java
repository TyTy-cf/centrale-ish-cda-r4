
package fr.cda.centaleish.entity;

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
    private String uuid;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String path;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Listing listing;

}
