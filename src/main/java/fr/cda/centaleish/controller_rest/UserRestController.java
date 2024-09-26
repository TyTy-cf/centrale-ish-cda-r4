package fr.cda.centaleish.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.dto.user.UserCreateDTO;
import fr.cda.centaleish.dto.user.UserUpdateDTO;
import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.json_views.JsonViews;
import fr.cda.centaleish.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @PostMapping
    @JsonView(JsonViews.UserShowView.class)
    public User create(@Valid @RequestBody UserCreateDTO dto) {
        return userService.create(dto);
    }

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

}
