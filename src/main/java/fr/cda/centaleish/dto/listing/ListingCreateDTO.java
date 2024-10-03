package fr.cda.centaleish.dto.listing;

import fr.cda.centaleish.dto.AddressDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListingCreateDTO extends ListingUpdateDTO {

    @NotBlank
    private AddressDTO address;

    @NotBlank
    private Long fuelId;

    @NotBlank
    private Long modelId;

}
