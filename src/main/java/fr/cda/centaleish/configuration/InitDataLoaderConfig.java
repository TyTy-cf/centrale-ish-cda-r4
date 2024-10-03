package fr.cda.centaleish.configuration;

import fr.cda.centaleish.entity.*;
import fr.cda.centaleish.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class InitDataLoaderConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final FuelRepository fuelRepository;
    private final AddressRepository addressRepository;
    private final ImageRepository imageRepository;
    private final ListingRepository listingRepository;
    private final FavoriteRepository favoriteRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final Faker faker;

    @Autowired
    public InitDataLoaderConfig(
        UserRepository userRepository,
        BrandRepository brandRepository,
        ModelRepository modelRepository,
        FuelRepository fuelRepository,
        AddressRepository addressRepository,
        ImageRepository imageRepository,
        ListingRepository listingRepository,
        FavoriteRepository favoriteRepository,
        BCryptPasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.fuelRepository = fuelRepository;
        this.addressRepository = addressRepository;
        this.imageRepository = imageRepository;
        this.listingRepository = listingRepository;
        this.favoriteRepository = favoriteRepository;
        this.passwordEncoder = passwordEncoder;
        this.faker = new Faker(Locale.of("fr"));
    }


    @Override
    public void run(String... args) {
        createFuels();
        createUsers();
        createAddresses();
        createBrands();
        createModels();
        createListings();
        createImages();
        createFavorites();
    }

    private void createAddresses() {
        List<String> duplicates = new ArrayList<>();
        if (addressRepository.count() == 0) {
            List<User> users = userRepository.findAll();
            for (long i = 1L; i <= 500L; i++) {
                Address address = new Address();
                address.setCity(faker.address().cityName());
                address.setLatitude(faker.address().latitude());
                address.setLongitude(faker.address().longitude());
                address.setStreetName(faker.address().streetName());
                address.setStreetNumber(faker.address().streetAddressNumber());
                address.setZipCode(faker.address().zipCode());
                Random random = new Random();
                if (random.nextBoolean()) {
                    User user;
                    do {
                        user = users.get(random.nextInt(1, 500));
                    } while (duplicates.contains(user.getUuid()));
                    duplicates.add(user.getUuid());
                    address.setUser(user);
                }
                addressRepository.save(address);
            }
            addressRepository.flush();
        }
    }

    private void createUsers() {
        if (userRepository.count() != 500) {
            for (long i = 1L; i <= 500L; i++) {
                User user = new User();
                user.setCreatedAt(LocalDateTime.now());
                user.setFirstName(faker.name().firstName());
                user.setLastName(faker.name().lastName());
                user.setEmail(faker.internet().emailAddress(
                    user.getFirstName() + "." + user.getLastName()
                ).toLowerCase());
                user.setPhone(faker.phoneNumber().cellPhone());
                user.setBirthAt(generateRandomDate(
                        Instant.now().minusSeconds(999999999)
                                .minusSeconds(999999999)
                                .minusSeconds(999999999)).toLocalDate());
                user.setPassword(passwordEncoder.encode("12345"));
                user.setSiret(faker.number().digits(14));
                String roles = "[\"ROLE_USER\"";
                if (i == 1L) {
                    roles += ", \"ROLE_ADMIN\"";
                }
                roles += "]";
                user.setRoles(roles);
                userRepository.save(user);
            }
            userRepository.flush();
        }
    }

    private void createBrands() {
        List<String> duplicates = new ArrayList<>();
        if (brandRepository.count() == 0) {
            for (long i = 1L; i <= 29L; i++) {
                Brand brand = new Brand();
                String name;
                do {
                    name = faker.vehicle().make();
                } while (duplicates.contains(name));
                duplicates.add(name);
                brand.setName(name);
                brandRepository.save(brand);
            }
            brandRepository.flush();
        }
    }

    private void createModels() {
        List<String> duplicates = new ArrayList<>();
        if (modelRepository.count() == 0) {
            for (int i = 0; i <= 160; i++) {
                Model model = new Model();
                Random random = new Random();
                Brand brand;
                String name;
                do {
                    brand = brandRepository.findById(random.nextLong(1L, 29L)).get();
                    name = faker.vehicle().model(brand.getName());
                } while (duplicates.contains(name));
                duplicates.add(name);
                model.setName(name);
                model.setBrand(brand);
                modelRepository.save(model);
            }
            modelRepository.flush();
        }
    }

    private void createFuels() {
        List<String> duplicates = new ArrayList<>();
        if (fuelRepository.count() < 7) {
            for (long i = 1L; i <= 7L; i++) {
                Fuel fuel = new Fuel();
                String name;
                do {
                    name = faker.vehicle().fuelType();
                } while (duplicates.contains(name));
                duplicates.add(name);
                fuel.setType(name);
                fuel.setLogo(faker.image().base64SVG().substring(0, 255));
                fuelRepository.save(fuel);
            }
            fuelRepository.flush();
        }
    }

    private void createListings() {
        List<User> users = userRepository.findAll();
        if (listingRepository.count() < 1000) {
            for (int i = 1; i <= 1000; i++) {
                Random random = new Random();
                Listing listing = new Listing();
                listing.setCreatedAt(LocalDateTime.now());
                Fuel fuel = fuelRepository.findById(random.nextLong(1L, 7L)).get();
                listing.setFuel(fuel);
                listing.setMileage(faker.number().numberBetween(5000L, 250000L));
                listing.setPrice(faker.number().numberBetween(250000L, 7000000L));
                listing.setProducedAt(generateRandomDate(
                        Instant.now().minusSeconds(999999999)));

                Model model = modelRepository.findById(random.nextLong(1L, 148L)).get();
                listing.setModel(model);

                Address address = addressRepository.findById(random.nextLong(1L, 500L)).get();
                listing.setAddress(address);

                listing.setDescription(faker.lorem().sentence(45, 9));

                User user = users.get(random.nextInt(1, 500));;
                listing.setOwner(user);

                listing.initTitle();

                listingRepository.save(listing);
            }
            listingRepository.flush();
        }
    }

    private void createImages() {
        List<Listing> listings = listingRepository.findAll();
        Random random = new Random();
        for (Listing listing : listings) {
            for (int i = 0; i < random.nextInt(1, 5); i++) {
                Image image = new Image();
                image.setPath(faker.image().base64PNG());
                image.setListing(listing);
                imageRepository.save(image);
            }
        }
        imageRepository.flush();
    }

    private void createFavorites() {
        List<User> users = userRepository.findAll();
        List<Listing> listings = listingRepository.findAll();
        Random random = new Random();
        for (User user : users) {
            for (int i = 0; i < random.nextInt(2, 8); i++) {
                Listing listing = listings.get(
                        random.nextInt(1, 1000));
                Favorite favorite = new Favorite();
                favorite.setCreatedAt(generateRandomDate(
                        Instant.now().minusSeconds(999999999)));
                favorite.setUser(user);
                favorite.setListing(listing);
                favoriteRepository.save(favorite);
            }
        }
        favoriteRepository.flush();
    }

    private LocalDateTime generateRandomDate(Instant start) {
        Faker faker = new Faker();
        Instant randomDate = faker.timeAndDate().between(
                start,
                Instant.now());
        return randomDate.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
