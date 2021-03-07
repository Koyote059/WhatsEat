package com.whatseat.food.diets;


import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class DietBuilder {

    private final Map<Integer, Map<DayOfWeek,MealPlan>> weekDayMealPlanMap = new HashMap<>();

    public void addWeeklyPlan(Map<DayOfWeek, MealPlan> weekMealPlanMap){
        int missingWeek = weekDayMealPlanMap.size();
        this.weekDayMealPlanMap.put(missingWeek,weekMealPlanMap);
    }

    public Diet build(){
        return new Diet(weekDayMealPlanMap);
    }
}
