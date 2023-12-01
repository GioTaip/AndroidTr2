package com.example.login;

public class UserData {

    private int user_id;
    private String name;
    private String mail;
    private String role;
    private int points;
    private String profile_pic;

    public UserData(int user_id, String name, String mail, String role, int points, String profile_pic) {
        this.user_id = user_id;
        this.name = name;
        this.mail = mail;
        this.role = role;
        this.points = points;
        this.profile_pic = profile_pic;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getRole() {
        return role;
    }

    public int getPoints() {
        return points;
    }

    public String getProfile_pic() {
        return profile_pic;
    }
}
