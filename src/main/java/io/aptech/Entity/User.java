package io.aptech.Entity;

import io.aptech.Enum.UserGender;

public class User {
    private int id;
    private UserGender gender;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private String image;

    public User() {
    }

    public User(UserGender gender, String fullName, String password, String email, String phone, String image) {
        this.gender = gender;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}