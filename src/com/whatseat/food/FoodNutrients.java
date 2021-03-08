package com.whatseat.food;

public class FoodNutrients {

    private final float fats, carbs, prots;

    FoodNutrients(float fats, float carbs, float prots){
        this.fats = fats;
        this.carbs = carbs;
        this.prots = prots;
    }


    public float getFats() {
        return fats;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getProts() {
        return prots;
    }

    public float getTotalCalories(){
        return (prots+carbs)*4+fats*9;
    }
}
