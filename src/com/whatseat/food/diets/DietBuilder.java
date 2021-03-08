package com.whatseat.food.diets;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietBuilder {

    private final List<WeeklyMealPlan> weekDayMealPlans = new ArrayList<>();

    public void addWeeklyPlan(WeeklyMealPlan weekMealPlan){
        this.weekDayMealPlans.add(weekMealPlan);
    }

    public Diet build(){
        return new Diet(weekDayMealPlans);
    }
}
