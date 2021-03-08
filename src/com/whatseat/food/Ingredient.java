package com.whatseat.food;

import com.whatseat.food.utils.Allergen;
import com.whatseat.food.utils.FoodType;
import com.whatseat.food.utils.QuantityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Ingredient implements Food {

    private final String name;

    private int price;
    private final QuantityType quantityType;
    private final FoodNutrients nutrientsPerHundredGrams;
    private final FoodType foodType;
    private final Set<Allergen> allergens;
    private final UUID uuid;

    protected Ingredient(String name, int pricePerHundredGrams, QuantityType quantityType,
                         FoodNutrients nutrientsPerHundredGrams, FoodType foodType, Set<Allergen> allergens, UUID uuid){
        this.name = name;
        this.price = pricePerHundredGrams;
        this.quantityType = quantityType;
        this.nutrientsPerHundredGrams = nutrientsPerHundredGrams;
        this.foodType = foodType;
        this.allergens = allergens;
        this.uuid = uuid;
    }


    public QuantityType getQuantityType(){
        return quantityType;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public List<Food> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public int getPrice(){
        return this.price;
    }

    @Override
    public FoodType getFoodType() {
        return foodType;
    }

    @Override
    public FoodNutrients getFoodNutrients() {
        return nutrientsPerHundredGrams;
    }

    @Override
    public Set<Allergen> getAllergens() {
        return allergens;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}
