package com.venky97vp.android.dietjanitor;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by Ganesh on 02-04-2017.
 */

public class Stats implements Parent<FoodDetails> {
    List<FoodDetails> details ;
    String nutrientName,required ,actual;
    public Stats(List<FoodDetails> details,String name,String required,String actual) {
        this.details = details;
        nutrientName = name;
        this.required = required;
        this.actual = actual;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Override
    public List<FoodDetails> getChildList() {
        return details;
    }

    public List<FoodDetails> getDetails() {
        return details;
    }

    public void setDetails(List<FoodDetails> details) {
        this.details = details;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
