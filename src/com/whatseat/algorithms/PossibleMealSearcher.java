package com.whatseat.algorithms;

import com.whatseat.food.Ingredient;
import com.whatseat.food.Meal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PossibleMealSearcher implements MealSearcher {

    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<Meal> meals = new ArrayList<>();

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void addIngredients(List<Ingredient> ingredients){ this.ingredients.addAll(ingredients); }

    public void addMeal(Meal meal){
        this.meals.add(meal);
    }

    public void addMeals(List<Meal> meals){
        this.meals.addAll(meals);
    }

    @Override
    public List<Meal> getMeals(int maxQuantity) {
        for(Meal meal: meals)
            Collections.sort(meals, new Comparator<Meal>() {
                @Override
                public int compare(Meal meal1, Meal meal2) {
                    int meal1MatchingIngredients = 0, meal2MatchingIngredients = 0;
                    List<Ingredient> meal1Ingredients = meal1.getIngredients();
                    List<Ingredient> meal2Ingredients = meal2.getIngredients();

                    for (Ingredient ingredient : meal1Ingredients) {
                        if (ingredients.contains(ingredient)) meal1MatchingIngredients += 1;
                    }

                    for (Ingredient ingredient : meal2Ingredients) {
                        if (ingredients.contains(ingredient)) meal2MatchingIngredients += 1;
                    }

                    return Integer.compare(meal1MatchingIngredients, meal2MatchingIngredients);
                }

            });
        return meals;
    }

}
