package com.whatseat.food;

import com.whatseat.food.utils.Allergen;
import com.whatseat.food.utils.FoodType;
import com.whatseat.food.utils.QuantityType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BasicIngredientBuilder {

    private final String name;
    private final Set<Allergen> allergens = new HashSet<>();

    private int price = 0;
    private QuantityType quantityType = QuantityType.GRAM;
    private FoodNutrients nutrientsPerHundredGrams = new FoodNutrients(0,0,0);
    private FoodType foodType = FoodType.OMNIVOROUS;
    private UUID uuid = UUID.randomUUID();

    public BasicIngredientBuilder(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setQuantityType(QuantityType quantityType){
        this.quantityType = quantityType;
    }

    public void setNutrientsPerHundredGrams(float fats, float carbs, float prot){
        this.nutrientsPerHundredGrams = new FoodNutrients(fats,carbs,prot);
    }

    public void setFoodType(FoodType foodType){
        this.foodType = foodType;
    }

    public void setUUID(UUID uuid){
        this.uuid = uuid;
    }

    public void addAllergen(Allergen allergen){
        this.allergens.add(allergen);
    }


    public BasicIngredient build(){
        return new BasicIngredient(name,price,quantityType,nutrientsPerHundredGrams,foodType,allergens,uuid);
    }
}
