package net.javaguides.springboot.services;


import net.javaguides.springboot.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getUserByRole();

    public boolean createUser(User user);

    public List<User> getUserByRoleCustomer();

}
