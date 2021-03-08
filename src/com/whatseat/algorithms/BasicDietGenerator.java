package com.whatseat.algorithms;

import com.whatseat.food.Meal;
import com.whatseat.food.diets.Diet;
import com.whatseat.food.diets.DietBuilder;
import com.whatseat.food.diets.WeeklyMealPlan;
import com.whatseat.food.FoodNutrients;
import com.whatseat.food.utils.FoodType;

import java.time.DayOfWeek;
import java.util.*;

public class BasicDietGenerator implements DietGenerator {

    protected int weekCount = 1;
    protected int mealsNumber = 3;
    protected FoodType foodType = FoodType.OMNIVOROUS;
    protected final FoodNutrients dailyNutrientsGoal;

    protected final Set<Meal> meals = new HashSet<>();

    public BasicDietGenerator(FoodNutrients dailyNutrientsGoal) {
        this.dailyNutrientsGoal = dailyNutrientsGoal;
    }

    public void setWeekCount(int weekCount) {
        this.weekCount = weekCount;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public void addMeal(Meal meal){
        this.meals.add(meal);
    }

    public void addMeals(List<Meal> mealList){
        this.meals.addAll(mealList);
    }

    public void removeMeal(Meal meal){
        this.meals.remove(meal);
    }

    public void setMealsNumber(int mealsNumber){
        this.mealsNumber = mealsNumber;
    }

    @Override
    public Diet generate() {
        {
            DietBuilder dietBuilder = new DietBuilder();
            float mealRequestedFats = dailyNutrientsGoal.getFats() / mealsNumber,
                    mealRequestedCarbs = dailyNutrientsGoal.getCarbs() / mealsNumber,
                    mealRequestedProt = dailyNutrientsGoal.getProts() / mealsNumber;
            for (int i = 0; i < weekCount; i++) {
                WeeklyMealPlan weeklyMealPlan = new WeeklyMealPlan();
                List<Meal> mealList = new ArrayList<>(meals);
                for (int j = 0; j < 7; j++) {
                    DayOfWeek dayOfWeek = DayOfWeek.of(j);
                    for (int k = 0; k < mealsNumber; k++) {
                        Meal preferredMeal = mealList.get(0);
                        for (Meal meal : mealList) {
                            FoodNutrients preferredMealNutrients = preferredMeal.getFoodNutrients();
                            FoodNutrients mealNutrients = meal.getFoodNutrients();

                            float preferredMealNutrientsGap = (Math.abs(preferredMealNutrients.getFats() - mealRequestedFats) +
                                    Math.abs(preferredMealNutrients.getCarbs() - mealRequestedCarbs) +
                                    Math.abs(preferredMealNutrients.getProts() - mealRequestedProt)) / 3;

                            float mealNutrientsGap = (Math.abs(mealNutrients.getFats() - mealRequestedFats) +
                                    Math.abs(mealNutrients.getCarbs() - mealRequestedCarbs) +
                                    Math.abs(mealNutrients.getProts() - mealRequestedProt)) / 3;

                            if (mealNutrientsGap < preferredMealNutrientsGap) preferredMeal = meal;
                        }
                        mealList.remove(preferredMeal);
                        weeklyMealPlan.addMeal(dayOfWeek,preferredMeal);
                    }
                    dietBuilder.addWeeklyPlan(weeklyMealPlan);
                }
            }
            return dietBuilder.build();
        }
    }
}
