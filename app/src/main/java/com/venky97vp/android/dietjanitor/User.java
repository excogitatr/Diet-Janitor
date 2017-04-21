package com.venky97vp.android.dietjanitor;

import java.util.Date;

/**
 * Created by venky on 25-03-2017.
 */

public class User {
    public String name,uid;
    public int activity,gender,age;
    public long dob;
    public double height,weight,BMI;
    public double Carbohydrates,Proteins,Fats,Cholestrol,Sodium,Calcium,Potassium ;
    public double Calories;
    public double Unit ;
    public double BMR = 100 ;
    public int oneDayProg;

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

    private void update(){
        this.calculateBMI();
        this.calculateBMR();
        //this.calculateProtien();
        //this.calculateFat();
        //this.calculateCarbohydrate();
    }

    public void calculateBMR(){
        double BMRT=(10*weight)+(6.25*height)-(5*age);
        BMR = (gender==0)? BMRT+5 :BMRT-161 ;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void calculateBMI(){
        BMI=weight/((height*height)/10000) ;
    }
}
