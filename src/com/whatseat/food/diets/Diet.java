package com.whatseat.food.diets;

import com.whatseat.food.Food;

import java.util.*;

public class Diet implements Food {

    private final List<WeeklyMealPlan> weeklyMealPlans;
    private final UUID uuid;

    Diet(List<WeeklyMealPlan> weeklyMealPlans){
        this.weeklyMealPlans = weeklyMealPlans;
        this.uuid = UUID.randomUUID();
    }

    Diet(List<WeeklyMealPlan> weeklyMealPlans, UUID uuid){
        this.weeklyMealPlans = weeklyMealPlans;
        this.uuid = uuid;
    }

    public WeeklyMealPlan getWeeklyMealPlan(int week){
        return weeklyMealPlans.get(week);
    }

    @Override
    public List<Food> getChildren() {
        List<Food> children = new ArrayList<>();
        for(WeeklyMealPlan weeklyMealPlan: weeklyMealPlans){
            children.addAll(weeklyMealPlan.getChildren());
        }
        return children;
    }

    public UUID getUUID() {
        return uuid;
    }
}
