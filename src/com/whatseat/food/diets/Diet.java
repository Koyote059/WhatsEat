package com.whatseat.food.diets;

import com.whatseat.food.Food;
import com.whatseat.food.utils.FoodNutrients;

import java.time.DayOfWeek;
import java.util.*;

public class Diet implements Food {

    private final Map<Integer,Map<DayOfWeek,MealPlan>> weekDayMealPlanMap;

    Diet(Map<Integer,Map<DayOfWeek,MealPlan>> weekDayMealPlanMap){
        this.weekDayMealPlanMap = weekDayMealPlanMap;
    }

    public MealPlan getMealPlan(int week, DayOfWeek dayOfWeek){
        return Objects.requireNonNull(weekDayMealPlanMap.get(week)).get(dayOfWeek);
    }

    @Override
    public FoodNutrients getFoodNutrients() { // FoodNutrientsPerWeek
        float totalFats = 0, totalCarbs = 0, totalProt = 0;
        for(Map.Entry<Integer, Map<DayOfWeek, MealPlan>> weekMapEntrySet: weekDayMealPlanMap.entrySet()){
            for(Map.Entry<DayOfWeek, MealPlan> dayOfWeekMealPlanEntrySet: weekMapEntrySet.getValue().entrySet()){
                MealPlan mealPlan = dayOfWeekMealPlanEntrySet.getValue();
                FoodNutrients mealPlanNutrients = mealPlan.getFoodNutrients();
                totalFats+=mealPlanNutrients.getFats();
                totalCarbs+=mealPlanNutrients.getCarbs();
                totalProt+=mealPlanNutrients.getProt();
            }
        }
        int weeksNumber = weekDayMealPlanMap.size();
        return new FoodNutrients(totalFats/weeksNumber,totalCarbs/weeksNumber,totalProt/weeksNumber);
    }

    @Override
    public int getPrice() { // Medium price per day
        int totalPrice = 0;
        List<Food> children = getChildren();
        for(Food child: children){
            totalPrice+=child.getPrice();
        }
        return totalPrice/children.size();
    }

    @Override
    public List<Food> getChildren() {
        Set<Food> children = new HashSet<>();
        for(Map.Entry<Integer, Map<DayOfWeek, MealPlan>> entrySet : weekDayMealPlanMap.entrySet()){
            for(Map.Entry<DayOfWeek, MealPlan> secondEntrySet: entrySet.getValue().entrySet()){
                children.add(secondEntrySet.getValue());
            }
        }
        return new ArrayList<>(children);
    }
}
