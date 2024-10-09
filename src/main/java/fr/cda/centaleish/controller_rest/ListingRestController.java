package fr.cda.centaleish.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.dto.listing.ListingCreateDTO;
import fr.cda.centaleish.dto.listing.ListingListDTO;
import fr.cda.centaleish.entity.Listing;
import fr.cda.centaleish.json_views.JsonViews;
import fr.cda.centaleish.service.ListingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/listing")
public class ListingRestController {

    private ListingService listingService;

    @GetMapping
    public Page<ListingListDTO> list(
        @PageableDefault(
            size = 12,
            sort = {"createdAt"},
            direction = Sort.Direction.DESC
        ) Pageable pageable
    ) {
        return listingService.list(pageable);
    }

    @PostMapping
    @JsonView(JsonViews.ListingShowView.class)
    public Listing create(@RequestBody ListingCreateDTO dto, Principal principal) {
        return listingService.create(dto, principal);
    }

}
