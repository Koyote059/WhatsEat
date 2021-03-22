package com.whatseat.food;

import com.whatseat.food.utils.Allergen;
import com.whatseat.food.utils.FoodType;
import com.whatseat.food.utils.QuantityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BasicIngredient extends Ingredient  {

    private int pricePerHundredGrams;
    private final QuantityType quantityType;
    private final FoodNutrients nutrientsPerHundredGrams;
    private final FoodType foodType;
    private final Set<Allergen> allergens;

    protected BasicIngredient(String name, int pricePerHundredGrams, QuantityType quantityType,
                              FoodNutrients nutrientsPerHundredGrams, FoodType foodType,
                              Set<Allergen> allergens, UUID uuid){
        super(name,uuid);
        this.pricePerHundredGrams = pricePerHundredGrams;
        this.quantityType = quantityType;
        this.nutrientsPerHundredGrams = nutrientsPerHundredGrams;
        this.foodType = foodType;
        this.allergens = allergens;
    }


    public QuantityType getQuantityType(){
        return quantityType;
    }

    @Override
    public List<Food> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public int getPrice(){
        return this.pricePerHundredGrams;
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

    @Override
    FoodNutrients getNutrientsPerGram() {
        return new FoodNutrients(nutrientsPerHundredGrams.getFats()/100,
                nutrientsPerHundredGrams.getCarbs()/100,
                nutrientsPerHundredGrams.getProts()/100);
    }
}
