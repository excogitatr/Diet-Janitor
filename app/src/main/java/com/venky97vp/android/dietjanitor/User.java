package com.venky97vp.android.dietjanitor;

import java.util.Date;

/**
 * Created by venky on 25-03-2017.
 */

public class User {
    public String name,uid;
    int activity,gender;
    public long dob;
    public double height,weight;

    public User(){
    }

    public User(String name, String uid, int activity, int gender, long dob, double height, double weight) {
        this.name = name;
        this.uid = uid;
        this.activity = activity;
        this.gender = gender;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
    }
}
