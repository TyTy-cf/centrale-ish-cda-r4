package fr.cda.centaleish.controller_rest;

import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @GetMapping("/{uuid}")
    public User show(@PathVariable String uuid) {
        return userService.findOneById(uuid);
    }

}
