package com.zxy.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Music {
    private int id;
    private String name;
    private String location;
    
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Music() {
    }
    
    public Music(String name) {
        this.name = name;
    }
    
    public Music(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
    
    /**
     *  name和location相同时，视为同一对象
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return
                Objects.equals(name, music.name) &&
                Objects.equals(location, music.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }
}
