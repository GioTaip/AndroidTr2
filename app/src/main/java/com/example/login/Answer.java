package com.example.login;
import com.google.gson.annotations.SerializedName;

public class Answer {

    private boolean authorization;

    private String name;

    public Answer(boolean authorization, String name) {
        this.authorization = authorization;
        this.name = name;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public String getName() {
        return name;
    }
}
