package fr.cda.centaleish.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.dto.user.UserCreateDTO;
import fr.cda.centaleish.dto.user.UserLoginDTO;
import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.json_views.JsonViews;
import fr.cda.centaleish.response.JwtResponse;
import fr.cda.centaleish.security.JwtAuthenticatorService;
import fr.cda.centaleish.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecurityRestController {

    private final UserService userService;
    private final JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping("/api/register")
    @JsonView(JsonViews.UserShowView.class)
    public User register(@Valid @RequestBody UserCreateDTO user) {
        return userService.create(user);
    }

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody UserLoginDTO user) {
        return jwtAuthenticatorService.authenticate(user);
    }

}