package net.javaguides.springboot.utils;

import net.javaguides.springboot.model.MyUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static MyUser getPrincipal() {
        MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;
    }
}
