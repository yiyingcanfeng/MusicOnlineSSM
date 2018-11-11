package com.zxy.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int permission;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getPermission() {
        return permission;
    }
    
    public void setPermission(int permission) {
        this.permission = permission;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", permission=" + permission +
                '}';
    }
    
    public User(String name, String password, String email, int permission) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.permission = permission;
    }
    
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public User(String name) {
        this.name = name;
    }
    
    public User() {
    }
}
