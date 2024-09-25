package fr.cda.centaleish.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteDTO {

    @NotBlank
    private String listingId;

    @NotBlank
    private String userId;

}
