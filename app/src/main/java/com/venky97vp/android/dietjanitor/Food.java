package com.venky97vp.android.dietjanitor;

/**
 * Created by Ganesh on 28-03-2017.
 */

public class Food {
    String name ;
    Double Carbohydrates,Proteins,Fats,Cholestrol,Sodium,Calcium,Potassium ;
    Double Calories;
    Double Unit ;

    public Food(String name,Double carbohydrates, Double proteins, Double fats, Double cholestrol, Double sodium, Double calcium, Double potassium, Double calories, Double unit) {
        this.name=name ;
        Carbohydrates = carbohydrates;
        Proteins = proteins;
        Fats = fats;
        Cholestrol = cholestrol;
        Sodium = sodium;
        Calcium = calcium;
        Potassium = potassium;
        Calories = calories;
        Unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCarbohydrates() {
        return Carbohydrates;
    }

    public Double getCalories() {
        return Calories;
    }

    public void setCalories(Double calories) {
        Calories = calories;
    }

    public Double getUnit() {
        return Unit;
    }

    public void setUnit(Double unit) {
        Unit = unit;
    }

    public void setCarbohydrates(Double carbohydrates) {
        Carbohydrates = carbohydrates;
    }

    public Double getProteins() {
        return Proteins;
    }

    public void setProteins(Double proteins) {
        Proteins = proteins;
    }

    public Double getFats() {
        return Fats;
    }

    public void setFats(Double fats) {
        Fats = fats;
    }

    public Double getCholestrol() {
        return Cholestrol;
    }

    public void setCholestrol(Double cholestrol) {
        Cholestrol = cholestrol;
    }

    public Double getSodium() {
        return Sodium;
    }

    public void setSodium(Double sodium) {
        Sodium = sodium;
    }

    public Double getCalcium() {
        return Calcium;
    }

    public void setCalcium(Double calcium) {
        Calcium = calcium;
    }

    public Double getPotassium() {
        return Potassium;
    }

    public void setPotassium(Double potassium) {
        Potassium = potassium;
    }
    public Double getCaloriesPer100(){
        return (Calories/Unit)*100 ;
    }
    public Double getCaloriesPerX(Double X){
        return (Calories/Unit)*X ;
    }
}
