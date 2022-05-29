package com.cy.kenny;

public class User {
    private String id_bimbel, email, name, password;

    public User(String id_bimbel, String email, String name, String password) {
        this.id_bimbel = id_bimbel;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getId_bimbel() {
        return id_bimbel;
    }

    public void setId_bimbel(String id_bimbel) {
        this.id_bimbel = id_bimbel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
