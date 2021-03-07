package com.whatseat.food;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MealsManager {

    private static MealsManager instance = null;

    public static MealsManager getInstance(){
        if(instance==null) instance = new MealsManager();
        return instance;
    }

    /* --------------------------------------------------------------------------------- */

    private Set<Meal> meals = new HashSet<>();

    public void addMeal(Meal meal){
        this.meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        this.meals.remove(meal);
    }

    public List<Meal> getMeals(){
        return new ArrayList<>(meals);
    }
}
