package fr.cda.centaleish.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDTO {

    @NotNull
    @Past
    private LocalDate birthAt;

    @NotBlank
    private String phone;

    @NotBlank
    private String siret;

    @NotBlank
    private String photo;

}
