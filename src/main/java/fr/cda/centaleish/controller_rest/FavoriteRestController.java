package fr.cda.centaleish.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.dto.FavoriteDTO;
import fr.cda.centaleish.dto.user.UserCreateDTO;
import fr.cda.centaleish.dto.user.UserUpdateDTO;
import fr.cda.centaleish.entity.Favorite;
import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.json_views.JsonViews;
import fr.cda.centaleish.service.FavoriteService;
import fr.cda.centaleish.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite")
@AllArgsConstructor
public class FavoriteRestController {

    private FavoriteService favoriteService;

    @PostMapping
    @JsonView(JsonViews.FavoriteView.class)
    public Favorite create(@Valid @RequestBody FavoriteDTO dto) {
        return favoriteService.create(dto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return favoriteService.delete(id);
    }

}
