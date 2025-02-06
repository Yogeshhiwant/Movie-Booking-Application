package com.infy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MovieScreen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int screenId;
    private String screenName;
    private int capacity;

    public MovieScreen(int screenId, String screenName, int capacity) {
        super();
        this.screenId = screenId;
        this.screenName = screenName;
        this.capacity = capacity;
    }

    public MovieScreen() {
        super();
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "MovieScreen [screenId=" + screenId + ", screenName=" + screenName + ", capacity=" + capacity + "]";
    }

}
