package fr.cda.centaleish.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.centaleish.entity.Listing;
import fr.cda.centaleish.json_views.JsonViews;
import fr.cda.centaleish.service.ListingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
