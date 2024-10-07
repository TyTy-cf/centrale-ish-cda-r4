package fr.cda.centaleish.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.dto.listing.ListingCreateDTO;
import fr.cda.centaleish.entity.Listing;
import fr.cda.centaleish.json_views.JsonViews;
import fr.cda.centaleish.service.ListingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/listing")
public class ListingRestController {

    private ListingService listingService;

    @GetMapping
    @JsonView(JsonViews.ListingListView.class)
    public List<Listing> list() {
        return listingService.list();
    }

    @PostMapping
    @JsonView(JsonViews.ListingShowView.class)
    public Listing create(@RequestBody ListingCreateDTO dto, Principal principal) {
        return listingService.create(dto, principal);
    }

}
