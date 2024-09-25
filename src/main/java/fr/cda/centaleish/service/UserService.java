package fr.cda.centaleish.service;

import fr.cda.centaleish.dto.user.UserCreateDTO;
import fr.cda.centaleish.dto.user.UserUpdateDTO;
import fr.cda.centaleish.entity.User;
import fr.cda.centaleish.repository.UserRepository;
import fr.cda.centaleish.service.interfaces.ServiceInterface;

public class UserService implements
        ServiceInterface<User, String, UserCreateDTO, UserUpdateDTO> {

    private UserRepository userRepository;

    @Override
    public User create(UserCreateDTO o) {
        return null;
    }

    @Override
    public User update(UserUpdateDTO o, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User findOneById(String id) {
        return null;
    }
}
