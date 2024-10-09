package fr.cda.centaleish.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.dto.user.UserUpdateDTO;
import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.json_views.JsonViews;
import fr.cda.centaleish.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @PutMapping("/{uuid}")
    @JsonView(JsonViews.UserShowView.class)
    public User update(@Valid @RequestBody UserUpdateDTO dto, @PathVariable String uuid) {
        return userService.update(dto, uuid);
    }

    @GetMapping("/activate/{code}")
    @JsonView(JsonViews.UserShowView.class)
    public User activate(@PathVariable String code) {
        return userService.activate(code);
    }

    @GetMapping("/{uuid}")
    @JsonView(JsonViews.UserShowView.class)
    public User show(@PathVariable String uuid) {
        return userService.findOneById(uuid);
    }

    @DeleteMapping("/{uuid}")
    public Boolean delete(@PathVariable String uuid) {
        return userService.delete(uuid);
    }

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploadPhoto(
        @RequestParam("userPhoto") MultipartFile file,
        Principal principal
    ) {
        return new ResponseEntity<>(userService.uploadImage(file, principal), HttpStatus.ACCEPTED);
    }

}
