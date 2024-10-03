package fr.cda.centaleish.service;

import fr.cda.centaleish.dto.user.UserCreateDTO;
import fr.cda.centaleish.dto.user.UserUpdateDTO;
import fr.cda.centaleish.entity.Address;
import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.exception.AlreadyActiveException;
import fr.cda.centaleish.exception.ExpiredCodeException;
import fr.cda.centaleish.repository.AddressRepository;
import fr.cda.centaleish.repository.UserRepository;
import fr.cda.centaleish.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements
        ServiceInterface<User, String, UserCreateDTO, UserUpdateDTO>,
        UserDetailsService {

    private UserRepository userRepository;

    private AddressRepository addressRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User create(UserCreateDTO o) {
        User user = new User();
        user.setRoles("[\"ROLE_USER\"]");
        user.setCreatedAt(LocalDateTime.now());
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPhone(o.getPhone());
        user.setPassword(passwordEncoder.encode(o.getPassword()));
        user.setEmail(o.getEmail());
        user.setFirstName(o.getFirstName());
        user.setLastName(o.getLastName());
        user.setBirthAt(o.getBirthAt());
        user.setActivationCodeSentAt(LocalDateTime.now());
        // Send mail ? mailerService.sendActivationCode(user);
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

    public User findOneByEmail(String email) {
        return userRepository.findUserByEmailAndActivationCodeIsNull(email)
                .orElseThrow(EntityNotFoundException::new);
    }

    public User activate(String code) {
        User user = userRepository.findUserByActivationCode(code)
                .orElseThrow(() -> new AlreadyActiveException("Ce code d'activation n'existe pas !"));

        LocalDateTime current = LocalDateTime.now();
        if (current.isAfter(user.getActivationCodeSentAt().plusMinutes(15))) {
            throw new ExpiredCodeException("La durée du code a expiré");
        }
        user.setActivationCode(null);
        user.setActivationCodeSentAt(null);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByEmailAndActivationCodeIsNull(username)
                .orElseThrow(() -> new UsernameNotFoundException("Echec de la connexion"));

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            user.getAuthorities()
        );
    }

}
