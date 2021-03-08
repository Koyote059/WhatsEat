package com.whatseat.food;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MealBuilder {

    protected final Map<Ingredient,Integer> ingredients = new HashMap<>();
    protected String name;
    protected Image pic = null;

    public MealBuilder(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPic(Image pic){
        this.pic = pic;
    }

    public void addIngredient(Ingredient ingredient, Integer quantity){ this.ingredients.put(ingredient,quantity); }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    public Meal build(){
        return new Meal(name,ingredients);
    }
}
