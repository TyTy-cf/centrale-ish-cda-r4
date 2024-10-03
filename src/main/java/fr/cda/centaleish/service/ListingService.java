package fr.cda.centaleish.service;

import fr.cda.centaleish.dto.listing.ListingCreateDTO;
import fr.cda.centaleish.dto.listing.ListingUpdateDTO;
import fr.cda.centaleish.entity.Listing;
import fr.cda.centaleish.repository.ListingRepository;
import fr.cda.centaleish.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListingService implements
        ServiceListInterface<Listing, String, ListingCreateDTO, ListingUpdateDTO> {

    private ListingRepository listingRepository;

    @Override
    public Listing create(ListingCreateDTO o) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Listing update(ListingUpdateDTO o, String id) {
        return null;
    }

    @Override
    public Listing findOneById(String id) {
        return listingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Listing> list() {
        return listingRepository.findAll();
    }
}
