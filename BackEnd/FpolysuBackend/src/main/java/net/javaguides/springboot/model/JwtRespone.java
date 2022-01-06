package net.javaguides.springboot.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;


public class JwtRespone {
    private String token;
    private String type = "Bearer";
    private String fullName;
    private List<GrantedAuthority> role;
    private String email;

    public JwtRespone(String token, String fullName, List<GrantedAuthority> role, String email) {
        this.token = token;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(List<GrantedAuthority> role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
