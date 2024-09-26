package fr.cda.centaleish.service;

import fr.cda.centaleish.dto.FavoriteDTO;
import fr.cda.centaleish.entity.Favorite;
import fr.cda.centaleish.entity.Listing;
import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.repository.FavoriteRepository;
import fr.cda.centaleish.repository.ListingRepository;
import fr.cda.centaleish.service.interfaces.BasicServiceInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class FavoriteService implements BasicServiceInterface<Favorite, Long, FavoriteDTO> {

    private FavoriteRepository favoriteRepository;

    private UserService userService;

    private ListingRepository listingRepository;

    @Override
    public Favorite create(FavoriteDTO o) {
        User user = userService.findOneById(o.getUserId());
        // TODO : appeler le ListingService ici pour être sûr !
        Listing listing = listingRepository.findById(o.getListingId()).get();
        Favorite favorite = new Favorite();
        favorite.setCreatedAt(LocalDateTime.now());
        favorite.setUser(user);
        favorite.setListing(listing);
        return favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            favoriteRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
