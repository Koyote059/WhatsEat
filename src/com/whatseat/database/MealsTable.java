package com.whatseat.database;

import com.whatseat.food.Ingredient;
import com.whatseat.food.Meal;

import javax.xml.crypto.Data;
import java.util.UUID;

public class MealsTable extends DatabaseTable {


    MealsTable(WhatsEatDatabase database) {
        super(database);
        String mealsTableQuery = "CREATE TABLE IF NOT EXISTS Meals";
        mealsTableQuery = "CREATE TABLE IF NOT EXISTS MealIngredients";
    }

    public void addMeal(Meal meal) {
        String name = meal.getName();
        UUID mealUUID = meal.getUUID();

        String query = String.format("INSERT INTO Meals VALUE (%s,%s)",name,mealUUID);

        for(Ingredient ingredient: meal.getIngredients()){
            UUID ingredientUUID = ingredient.getUUID();
            int quantity = meal.getIngredientQuantity(ingredient);
            query = String.format("INSERT INTO MealIngredients VALUE (%s,%s,%d)",mealUUID,ingredientUUID,quantity);
        }

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

