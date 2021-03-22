package com.whatseat.food;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MealBuilder {

    protected final Map<Ingredient,Float> ingredients = new HashMap<>();
    protected String name;
    protected UUID uuid = UUID.randomUUID();

    public MealBuilder(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addIngredient(Ingredient ingredient, Float quantity){
        this.ingredients.put(ingredient,quantity);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    public void setUUID(UUID uuid){
        this.uuid = uuid;
    }

    public Meal build(){
        return new Meal(name,ingredients,uuid);
    }
}
