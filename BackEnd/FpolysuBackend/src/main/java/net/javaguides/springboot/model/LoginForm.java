package net.javaguides.springboot.model;

import javax.validation.constraints.NotBlank;

public class LoginForm {
    @NotBlank(message = "userName is mandatory")
    private String email;

    @NotBlank(message = "passWord is mandatory")
    private String passWord;

    public LoginForm() {
    }

    public LoginForm(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
