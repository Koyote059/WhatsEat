package com.whatseat.food.diets;

import com.whatseat.food.Food;
import com.whatseat.food.utils.FoodNutrients;

import java.time.DayOfWeek;
import java.util.*;

public class Diet implements Food {

    private final List<WeeklyMealPlan> weeklyMealPlans;

    Diet(List<WeeklyMealPlan> weeklyMealPlans){
        this.weeklyMealPlans = weeklyMealPlans;
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
}
