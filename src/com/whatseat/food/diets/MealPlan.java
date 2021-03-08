package com.whatseat.food.diets;

import com.whatseat.food.Food;
import com.whatseat.food.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealPlan implements Food {

    protected final List<Meal> meals = new ArrayList<>();

    public void addMeal(Meal meal){
        this.meals.add(meal);
    }

    public void removeMeal(Meal meal){
        this.meals.remove(meal);
    }

    public List<Meal> getMeals(){
        return meals;
    }

    @Override
    public List<Food> getChildren() {
        return new ArrayList<>(meals);
    }
}
