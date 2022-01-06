package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUserByRole() {
        List<User> listUsers = userRepository.getUserByRole();
        return listUsers;
    }

    @Override
    public boolean createUser(User user) {
        boolean result = false;
        try {
            userRepository.save(user);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getUserByRoleCustomer() {
        List<User> listUsers = userRepository.getUserByRoleCustomer();
        return listUsers;
    }
}
