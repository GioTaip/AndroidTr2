package com.example.login;

public class UserRequestLogin {
    private String mail;
    private String password;

    public UserRequestLogin(String mail) {
        this.mail = mail;
    }

    public UserRequestLogin(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
