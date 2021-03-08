package com.whatseat.database;

import com.whatseat.food.Ingredient;

import java.util.List;

public class IngredientsTable extends DatabaseTable {

    IngredientsTable(WhatsEatDatabase database) {
        super(database);
    }

    @Override
    public void create() {

    }

    public void addIngredient(Ingredient ingredient){

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
