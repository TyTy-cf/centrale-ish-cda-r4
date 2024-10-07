package fr.cda.centaleish.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    private Long id;

    @NotBlank
    private String city;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;

    @NotBlank
    private String streetName;

    @NotBlank
    private String streetNumber;

    @NotBlank
    private String zipCode;

}
