package com.whatseat.database;

import com.whatseat.food.Meal;

import javax.xml.crypto.Data;

public class MealsTable extends DatabaseTable {


    MealsTable(WhatsEatDatabase database) {
        super(database);
    }

    @Override
    public void create() {

    }

    public void addMeal(Meal meal) {

    }

    public Meal getMeal(String name) {
        return null;
    }

    // Actual MealsTable
    /* ----------- */
    /* UUID | Name */
    /* ----------- */

    // Meals - Ingredients bond table
    /* ----------------------------------------- */
    /* MealUUID | IngredientUUID | Quantity (gr) */
    /* ----------------------------------------- */
}

