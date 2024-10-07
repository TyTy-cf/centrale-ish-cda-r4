package fr.cda.centaleish.service;

import fr.cda.centaleish.dto.listing.ListingCreateDTO;
import fr.cda.centaleish.dto.listing.ListingUpdateDTO;
import fr.cda.centaleish.entity.*;
import fr.cda.centaleish.repository.FuelRepository;
import fr.cda.centaleish.repository.ImageRepository;
import fr.cda.centaleish.repository.ListingRepository;
import fr.cda.centaleish.repository.ModelRepository;
import fr.cda.centaleish.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ListingService implements
        ServiceListInterface<Listing, String, ListingCreateDTO, ListingUpdateDTO> {

    private ListingRepository listingRepository;
    private ModelRepository modelRepository;
    private FuelRepository fuelRepository;
    private AddressService addressService;
    private UserService userService;
    private ImageRepository imageRepository;

    public Listing create(ListingCreateDTO dto, Principal principal) {
        Listing listing = create(dto);
        User user = userService.findOneByEmail(principal.getName());
        listing.setOwner(user);
        return listingRepository.saveAndFlush(listing);
    }

    @Override
    public Listing create(ListingCreateDTO dto) {
        Listing listing = new Listing();
        listing.setCreatedAt(LocalDateTime.now());
        listing.setPrice(dto.getPrice());
        listing.setMileage(dto.getMileage());
        listing.setDescription(dto.getDescription());
        listing.setProducedAt(dto.getProducedAt());

        Model model = modelRepository.findById(dto.getModelId())
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        listing.setModel(model);

        Fuel fuel = fuelRepository.findById(dto.getFuelId())
                .orElseThrow(() -> new EntityNotFoundException("Fuel not found"));
        listing.setFuel(fuel);

        Address address;
        Long addressId = dto.getAddress().getId();
        if (addressId != null) { // Address already exists
            address = addressService.findOneById(addressId);
        } else {
            address = addressService.create(dto.getAddress());
        }
        listing.setAddress(address);
        listing.initTitle();

//        User user = userService.findOneById(dto.getUserUuid());
//        listing.setOwner(user);
        return listing;
    }

    @Override
    public Boolean delete(String id) {
        try {
            Listing listing = findOneById(id);
            listing.setTitle("Annonce supprimée");
            listing.setDescription("Annonce supprimée");
            listing.setProducedAt(LocalDateTime.now());
            listing.setMileage(0L);
            listing.setPrice(0L);
            imageRepository.deleteAll(listing.getImages());
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    @Override
    public Listing update(ListingUpdateDTO dto, String id) {
        Listing listing = findOneById(id);
        listing.setPrice(dto.getPrice());
        listing.setMileage(dto.getMileage());
        listing.setDescription(dto.getDescription());
        listing.setProducedAt(dto.getProducedAt());

        Address address;
        Long addressId = dto.getAddress().getId();
        if (addressId != null) { // Address already exists
            address = addressService.findOneById(addressId);
        } else {
            address = addressService.create(dto.getAddress());
        }
        listing.setAddress(address);
        return listingRepository.saveAndFlush(listing);
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
