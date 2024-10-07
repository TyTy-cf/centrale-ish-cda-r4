package fr.cda.centaleish.controller_rest.admin;


import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.dto.BrandDTO;
import fr.cda.centaleish.entity.Brand;
import fr.cda.centaleish.json_views.JsonViews;
import fr.cda.centaleish.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/brand")
public class BrandController {

    private BrandService brandService;

    @GetMapping
    @JsonView(JsonViews.BrandListView.class)
    public List<Brand> list() {
        return brandService.list();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.BrandShowView.class)
    public Brand show(@PathVariable Long id) {
        return brandService.findOneById(id);
    }

    @PostMapping
    public Brand create(@RequestBody BrandDTO dto) {
        return brandService.create(dto);
    }

    @PutMapping("/{id}")
    public Brand update(@RequestBody BrandDTO dto, @PathVariable Long id) {
        return brandService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return brandService.delete(id);
    }

}
