package com.whatseat.food.diets;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DietBuilder {

    private final List<WeeklyMealPlan> weekDayMealPlans = new ArrayList<>();
    private UUID uuid = UUID.randomUUID();

    public void addWeeklyPlan(WeeklyMealPlan weekMealPlan){
        this.weekDayMealPlans.add(weekMealPlan);
    }

    public void setUUID(UUID uuid){
        this.uuid = uuid;
    }

    public Diet build(){
        return new Diet(weekDayMealPlans,uuid);
    }
}
