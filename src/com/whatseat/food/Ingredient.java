package com.whatseat.food;

import com.whatseat.food.utils.Allergens;
import com.whatseat.food.utils.FoodNutrients;
import com.whatseat.food.utils.FoodType;
import com.whatseat.food.utils.QuantityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Ingredient implements Food {

    private final String name;

    private int price;
    private final QuantityType quantityType;
    private final FoodNutrients nutrientsPerHundredGrams;
    private final FoodType foodType;
    private final Set<Allergens> allergens;

    public Ingredient(String name, int pricePerHundredGrams, QuantityType quantityType,
                      FoodNutrients nutrientsPerHundredGrams, FoodType foodType, Set<Allergens> allergens){
        this.name = name;
        this.price = pricePerHundredGrams;
        this.quantityType = quantityType;
        this.nutrientsPerHundredGrams = nutrientsPerHundredGrams;
        this.foodType = foodType;
        this.allergens = allergens;
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
    public Set<Allergens> getAllergens() {
        return allergens;
    }

}
