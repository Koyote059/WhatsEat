package com.whatseat.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Meal implements Food {

    protected final Map<Ingredient, Integer> ingredientsQuantityMap;
    protected final String name;
    protected final UUID uuid;

    Meal(String name, Map<Ingredient, Integer> ingredients, UUID uuid){
        this.name = name;
        this.ingredientsQuantityMap = ingredients;
        this.uuid = uuid;
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

    public int getIngredientQuantity(Ingredient ingredient) {
        return this.ingredientsQuantityMap.get(ingredient);
    }
}
