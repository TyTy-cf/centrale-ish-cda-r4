package fr.cda.centaleish.dto.listing;

import fr.cda.centaleish.dto.AddressDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListingUpdateDTO {

    @NotBlank
    private String description;

    @NotBlank
    private Long mileage;

    @NotBlank
    private Long price;

    private LocalDateTime producedAt;

    @NotBlank
    private AddressDTO address;

    private String userUuid;

}
