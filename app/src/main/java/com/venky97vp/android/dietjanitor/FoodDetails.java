package com.venky97vp.android.dietjanitor;

/**
 * Created by Ganesh on 02-04-2017.
 */

class FoodDetails {
     String FoodName ;
     String Value ;

    public FoodDetails(String foodName, String value) {
        FoodName = foodName;
        Value = value;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
