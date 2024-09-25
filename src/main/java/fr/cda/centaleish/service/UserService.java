package fr.cda.centaleish.service;

import fr.cda.centaleish.dto.user.UserCreateDTO;
import fr.cda.centaleish.dto.user.UserUpdateDTO;
import fr.cda.centaleish.entity.Address;
import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.exception.AlreadyActiveException;
import fr.cda.centaleish.repository.AddressRepository;
import fr.cda.centaleish.repository.UserRepository;
import fr.cda.centaleish.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements
        ServiceInterface<User, String, UserCreateDTO, UserUpdateDTO> {

    private UserRepository userRepository;

    private AddressRepository addressRepository;

    @Override
    public User create(UserCreateDTO o) {
        User user = new User();
        user.setRoles("[\"ROLE_USER\"]");
        user.setCreatedAt(LocalDateTime.now());
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPhone(o.getPhone());
        user.setPassword(o.getPassword());
        user.setEmail(o.getEmail());
        user.setFirstName(o.getFirstName());
        user.setLastName(o.getLastName());
        user.setBirthAt(o.getBirthAt());
        // Send mail ?
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(UserUpdateDTO o, String id) {
        User user = findOneById(id);
        user.setPhoto(o.getPhoto());
        user.setSiret(o.getSiret());
        user.setPhone(o.getPhone());
        user.setBirthAt(o.getBirthAt());
        user.setFirstName(o.getFirstName());
        user.setLastName(o.getLastName());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Boolean delete(String uuid) {
        try {
            User user = findOneById(uuid);
            user.setPhone(null);
            user.setBirthAt(null);
            user.setPhoto(null);
            user.setSiret(null);
            user.setLastName(null);
            user.setFirstName(null);
            user.setEmail("Utilisateur supprimé");
            Address address = user.getAddress();
            if (address!= null) {
                address.setUser(null);
                addressRepository.save(address);
            }
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User findOneById(String id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public User activate(String code) {
        User user = userRepository.findUserByActivationCode(code)
                .orElseThrow(AlreadyActiveException::new);

        user.setActivationCode(null);
        return userRepository.saveAndFlush(user);
    }
}
