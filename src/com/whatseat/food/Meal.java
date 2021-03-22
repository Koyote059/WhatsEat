package com.whatseat.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Meal extends Ingredient {

    protected final Map<Ingredient, Float> ingredientsQuantityMap;

    Meal(String name, Map<Ingredient, Float> ingredients, UUID uuid){
        super(name,uuid);
        this.ingredientsQuantityMap = ingredients;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public int getPrice(){
        int price = 0;
        for(Map.Entry<Ingredient, Float> foodEntrySet: ingredientsQuantityMap.entrySet()){
            int foodPricePer100Grams = foodEntrySet.getKey().getPrice();
            price+=foodPricePer100Grams* foodEntrySet.getValue();
        }
        return price;
    }

    @Override
    public FoodNutrients getFoodNutrients() {
        float carbs = 0, prot = 0, fats = 0;
        for(Map.Entry<Ingredient, Float> foodEntrySet: ingredientsQuantityMap.entrySet()){
            FoodNutrients foodNutrients = foodEntrySet.getKey().getNutrientsPerGram();
            float grams = foodEntrySet.getValue();
            carbs+=foodNutrients.getCarbs() * grams;
            prot+=foodNutrients.getProts() * grams;
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

    public UUID getUUID() {
        return uuid;
    }

    public float getIngredientQuantity(Ingredient ingredient) {
        return this.ingredientsQuantityMap.get(ingredient);
    }

    @Override
    FoodNutrients getNutrientsPerGram() {
        float fats = 0, carbs = 0, prot = 0;
        for(Map.Entry<Ingredient, Float> foodEntrySet: ingredientsQuantityMap.entrySet()){
            FoodNutrients foodNutrients = foodEntrySet.getKey().getNutrientsPerGram();
            carbs+=foodNutrients.getCarbs();
            prot+=foodNutrients.getProts();
            fats+=foodNutrients.getFats();
        }
        return new FoodNutrients(fats,carbs,prot);
    }
}
