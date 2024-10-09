package fr.cda.centaleish.dto.listing;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListingListDTO {

    private String uuid;

    private String title;

    private String price;

    private Long mileage;

    private LocalDateTime producedAt;

    private String zipCode;

    private String model;

    private String brand;

    private String fuel;

}