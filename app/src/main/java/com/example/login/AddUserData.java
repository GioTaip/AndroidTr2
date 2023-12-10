package com.example.login;

public class AddUserData {
    private String name;
    private String mail;
    private String password;
    private String role;
    private int points;

    public AddUserData(String nom, String contrasenya, String correo, String selectedRole, int punts) {
        this.name = nom;
        this.password = contrasenya;
        this.mail = correo;
        this.role = selectedRole;
        this.points = punts;
    }


    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    public int getPoints() {
        return points;
    }

}
