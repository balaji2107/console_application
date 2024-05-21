package com.example.model;

public class Lift {
    public String liftName;
    public int position;

    public Lift(String liftName,int position) {
        this.liftName = liftName;
        this.position = position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
