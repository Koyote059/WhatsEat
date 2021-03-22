package com.whatseat.food;

import java.util.UUID;

public abstract class Ingredient implements Food {

    protected final UUID uuid;
    protected final String name;

    public Ingredient(String name, UUID uuid){
        this.uuid = uuid;
        this.name = name;
    }


    public UUID getUUID() {
        return this.uuid;
    }

    public String getName(){ return this.name; }

    abstract FoodNutrients getNutrientsPerGram();
}
