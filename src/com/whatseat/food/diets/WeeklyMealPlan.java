package com.whatseat.food.diets;

import com.whatseat.food.Food;
import com.whatseat.food.Meal;

import java.time.DayOfWeek;
import java.util.*;

public class WeeklyMealPlan implements Food {

    protected Map<DayOfWeek,MealPlan> weekMealPlanMap = new HashMap<>();

    public void addMeal(DayOfWeek day, Meal meal){
        if(!weekMealPlanMap.containsKey(day)) weekMealPlanMap.put(day,new MealPlan());
        MealPlan mealPlan = weekMealPlanMap.get(day);
        mealPlan.addMeal(meal);
    }

    public MealPlan getMealPlan(DayOfWeek day){
        return weekMealPlanMap.get(day);
    }

    @Override
    public List<Food> getChildren() {
        List<Food> children = new ArrayList<>();
        for(Map.Entry<DayOfWeek, MealPlan> weekMealPlanEntry: weekMealPlanMap.entrySet()){
            MealPlan mealPlan = weekMealPlanEntry.getValue();
            children.add(mealPlan);
        }
        return children;
    }

}
