package com.whatseat.database;

import com.whatseat.food.*;
import com.whatseat.food.utils.FoodType;

import javax.sql.rowset.CachedRowSet;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class FoodsTable extends DatabaseTable {

    FoodsTable(WhatsEatDatabase database) {
        super(database);
        String basicIngredientsQuery = "CREATE TABLE IF NOT EXISTS BasicIngredients (" +
                "UUID TEXT PRIMARY KEY," +
                "Name TEXT NULL NOT NULL," +
                "FoodType TEXT NOT NULL," +
                "FatsPer100G FLOAT NOT NULL," +
                "CarbsPer100G FLOAT NOT NULL," +
                "ProtsPer100G FLOAT Not NULL," +
                ");";
        String mealsQuery = "CREATE TABLE IF NOT EXISTS Meals (" +
                "UUID TEXT PRIMARY KEY," +
                "Name TEXT NOT NULL" +
                ");";
        String ingredientsMealsBondQuery = "CREATE TABLE IF NOT EXISTS IngredientsMealsBond (" +
                "MealUUID TEXT NOT NULL," +
                "IngredientUUID TEXT NOT NULL," +
                "QUANTITY FLOAT NOT NULL" + // Todo -> aggiusta keys
                ");";

        executeQueries(basicIngredientsQuery,mealsQuery,ingredientsMealsBondQuery);
    }

    public void addBasicIngredient(BasicIngredient ingredient){
        String name = ingredient.getName();
        UUID uuid = ingredient.getUUID();
        FoodType foodType = ingredient.getFoodType();
        FoodNutrients foodNutrients = ingredient.getFoodNutrients();
        float prots = foodNutrients.getProts();
        float carbs = foodNutrients.getCarbs();
        float fats = foodNutrients.getFats();

        String query = String.format("INSERT INTO BasicIngredients VALUES (%s,%s,,%s,%f,%f,%f);",
                uuid,name,foodType, prots,carbs,fats);
        executeQueries(query);
    }

    public void addMeal(Meal meal){
        String mealName = meal.getName();
        UUID mealUUID = meal.getUUID();

        int mealQuantity = meal.getIngredients().size();
        String[] mealsQueries = new String[mealQuantity+1];
        mealsQueries[0] = String.format("INSERT INTO Meal VALUES (%s,%s);",mealUUID,mealName);

        List<Ingredient> ingredientList = meal.getIngredients();
        for(int i = 0; i<mealQuantity;i++){
            Ingredient ingredient = ingredientList.get(i);
            UUID ingredientUUID = ingredient.getUUID();
            float ingredientQuantity = meal.getIngredientQuantity(ingredient);
            mealsQueries[i+1] = String.format("INSERT INTO IngredientsMealsBond VALUES (%s,%s,%f);",
                    mealUUID,ingredient,ingredientQuantity);
        }

        executeQueries(mealsQueries);
    }

    public BasicIngredient getIngredient(String name) throws SQLException {
        String ingredientQuery = "SELECT * FROM BasicIngredients WHERE Name=\" "+ name +"\"";
        return getBasicIngredient(ingredientQuery);
    }

    public BasicIngredient getIngredient(UUID uuid) throws SQLException {
        String ingredientQuery = "SELECT * FROM BasicIngredients WHERE UUID=\" "+ uuid +"\"";
        return getBasicIngredient(ingredientQuery);
    }

    private BasicIngredient getBasicIngredient(String ingredientQuery) throws SQLException {
        CachedRowSet result = executeQueryWithResult(ingredientQuery);
        if(!result.next()) return null;
        UUID ingredientUUID = UUID.fromString(result.getString("UUID"));
        String ingredientName = result.getString("Name"); // Todo -> Remove name Variable
        FoodType foodType = FoodType.valueOf(result.getString("FoodType"));
        float fatsPer100G = result.getFloat("FatsPer100G");
        float carbsPer100G = result.getFloat("CarbsPer100G");
        float protsPer100G = result.getFloat("ProtsPer100G");
        BasicIngredientBuilder builder = new BasicIngredientBuilder(ingredientName);
        builder.setUUID(ingredientUUID);
        builder.setFoodType(foodType);
        builder.setNutrientsPerHundredGrams(fatsPer100G,carbsPer100G,protsPer100G);
        return builder.build();
    }

    public Meal getMeal(String name) throws SQLException {
        String mealsQuery = String.format("SELECT UUID FROM Meals WHERE Name=\"%s\"",name);
        CachedRowSet rowSet = executeQueryWithResult(mealsQuery);
        if(!rowSet.next()) return null; // Todo -> Exception
        UUID mealUUID = UUID.fromString(rowSet.getString("UUID"));
        return getMeal(mealUUID, name);
    }

    public Meal getMeal(UUID uuid) throws SQLException {
        String mealsQuery = String.format("SELECT Name FROM Meals WHERE UUID=\"%s\"",uuid);
        CachedRowSet rowSet = executeQueryWithResult(mealsQuery);
        if(!rowSet.next()) return null; // Todo -> Exception
        String name = rowSet.getString("Name");
        return getMeal(uuid, name);
    }

    private Meal getMeal(UUID uuid, String name) throws SQLException {
        String mealsQuery;
        CachedRowSet rowSet;
        MealBuilder builder = new MealBuilder(name);
        builder.setUUID(uuid);

        mealsQuery = String.format("SELECT FoodUUID, Quantity FROM IngredientsMealsBond WHERE MealUUID=\"%s\"",uuid);
        rowSet = executeQueryWithResult(mealsQuery);

        while(rowSet.next()){
            UUID ingredientUUID = UUID.fromString(rowSet.getString("FoodUUID"));
            float quantity = rowSet.getFloat("Quantity");
            Ingredient ingredient = getIngredient(ingredientUUID);
            if(ingredient==null){
                ingredient = getMeal(ingredientUUID);
                if(ingredient==null) return null; // Todo -> Throw Exception
            }
            builder.addIngredient(ingredient,quantity);
        }
        return builder.build();
    }

    /* -------- BasicIngredients ----------------------------------------- */
    /*  UUID | Name | FoodType | FatsPer100G | CarbsPer100G | ProtsPer100G */
    /*  ------------------------------------------------------------------ */

    /* --------------------------- Meals ---------------------------------- */
    /* UUID | Name                                                          */
    /* -------------------------------------------------------------------- */

    /* ------------------- IngredientsMealsBond --------------------------- */
    /* MealUUID | FoodUUID | Quantity                                       */
    /* -------------------------------------------------------------------- */

}

