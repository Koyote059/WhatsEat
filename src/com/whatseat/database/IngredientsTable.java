package com.whatseat.database;

import com.whatseat.food.Ingredient;
import com.whatseat.food.FoodNutrients;
import com.whatseat.food.utils.FoodType;

import java.util.List;
import java.util.UUID;

public class IngredientsTable extends DatabaseTable {

    IngredientsTable(WhatsEatDatabase database) {
        super(database);
        String query = "CREATE TABLE IF NOT EXISTS Ingredients";

    }


    public void addIngredient(Ingredient ingredient){
        String name = ingredient.getName();
        UUID uuid = ingredient.getUUID();
        int price = ingredient.getPrice();
        FoodNutrients foodNutrients = ingredient.getFoodNutrients();
        float prots = foodNutrients.getProts();
        float carbs = foodNutrients.getCarbs();
        float fats = foodNutrients.getFats();
        FoodType foodType = ingredient.getFoodType();

        String query = String.format("INSERT INTO Ingredients VALUES (%s,%s,%f,%f,%f,%d,%s)",
                uuid,name,prots,carbs,fats,price,foodType);
    }

    public void addIngredients(List<Ingredient> ingredients){

    }

    public Ingredient getIngredient(String name){
        return null;
    }

    /* --------------------------------------------------------*/
    /*  UUID | Name | Prots | Carbs | Fats | Price | FoodType  */
    /*  -------------------------------------------------------*/

}
