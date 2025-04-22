package model;

public class User {
    public String username;
    public String password;
    public String role; // admin or user

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
