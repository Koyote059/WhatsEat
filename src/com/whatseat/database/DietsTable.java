package com.whatseat.database;

import com.whatseat.food.diets.Diet;

import javax.xml.crypto.Data;

public class DietsTable extends DatabaseTable {

    DietsTable(WhatsEatDatabase database) {
        super(database);
    }

    @Override
    public void create() {

    }

    public void addDiet(Diet diet){

    }

    public Diet getDiet(String name){
        return null;
    }

    // Actual DietTable
    /* ----------- */
    /* UUID | Name */
    /* ----------- */

    // Diet - Meals bond table
    /* ------------------------------- */
    /* DietUUID | MealUUID | DayOfWeek */
    /* ------------------------------- */
}
