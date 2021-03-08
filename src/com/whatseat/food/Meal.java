package com.whatseat.food;

import com.whatseat.food.utils.FoodNutrients;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Meal implements Food {

    protected final Map<Ingredient, Integer> ingredientsQuantityMap;
    protected final String name;

    protected Meal(String name, Map<Ingredient, Integer> ingredients){
        this.name = name;
        this.ingredientsQuantityMap = ingredients;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public int getPrice(){
        int price = 0;
        for(Map.Entry<Ingredient, Integer> foodEntrySet: ingredientsQuantityMap.entrySet()){
            int foodPricePer100Grams = foodEntrySet.getKey().getPrice();
            price+=foodPricePer100Grams* foodEntrySet.getValue();
        }
        return price;
    }

    @Override
    public FoodNutrients getFoodNutrients() {
        float carbs = 0, prot = 0, fats = 0;
        for(Map.Entry<Ingredient, Integer> foodEntrySet: ingredientsQuantityMap.entrySet()){
            FoodNutrients foodNutrients = foodEntrySet.getKey().getFoodNutrients();
            int grams = foodEntrySet.getValue();
            carbs+=foodNutrients.getCarbs() * grams;
            prot+=foodNutrients.getProt() * grams;
            fats+=foodNutrients.getFats() * grams;

        }
        return new FoodNutrients(fats,carbs,prot);
    }

    public List<Ingredient> getIngredients(){
        return new ArrayList<>(ingredientsQuantityMap.keySet());
    }

    @Override
    public List<Food> getChildren() {
        return new ArrayList<>(ingredientsQuantityMap.keySet());
    }
}
