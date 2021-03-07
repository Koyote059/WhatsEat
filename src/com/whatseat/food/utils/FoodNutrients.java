package com.whatseat.food.utils;

public class FoodNutrients {

    private final float fats, carbs, prot;

    public FoodNutrients(float fats, float carbs, float prot){
        this.fats = fats;
        this.carbs = carbs;
        this.prot = prot;
    }


    public float getFats() {
        return fats;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getProt() {
        return prot;
    }

    public float getTotalCalories(){
        return (prot+carbs)*4+fats*9;
    }
}
