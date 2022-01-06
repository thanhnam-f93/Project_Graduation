package net.javaguides.springboot.security;

import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.model.MyUser;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        List<GrantedAuthority> authorities = new ArrayList();

        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        MyUser myUser = new MyUser(user.getEmail(), user.getPassword(),
                true, true, true, true, authorities);
        myUser.setFullName(user.getFullName());
        myUser.setRole(authorities);
        myUser.setEmail(user.getEmail());
        return myUser;

    }

}