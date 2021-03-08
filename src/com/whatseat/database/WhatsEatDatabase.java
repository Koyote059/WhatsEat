package com.whatseat.database;

import com.whatseat.food.Ingredient;
import com.whatseat.food.Meal;
import com.whatseat.food.diets.Diet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhatsEatDatabase {

    private static Map<String,WhatsEatDatabase> databaseMap = new HashMap<>();

    public static WhatsEatDatabase getInstance(String name){
        if(!databaseMap.containsKey(name)) databaseMap.put(name, new WhatsEatDatabase(name));
        return databaseMap.get(name);
    }

    protected final String name;

    public WhatsEatDatabase(String name) {
        this.name = name;
    }

}
