package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.JwtRespone;
import net.javaguides.springboot.model.LoginForm;
import net.javaguides.springboot.security.jwt.JwtProvider;
import net.javaguides.springboot.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin()
public class LoginController {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    public LoginController(JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Validated @RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginForm.getEmail(),
                        loginForm.getPassWord())
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtRespone(jwt, SecurityUtils.getPrincipal().getFullName(), SecurityUtils.getPrincipal().getRole(), SecurityUtils.getPrincipal().getEmail()));

    }

}
