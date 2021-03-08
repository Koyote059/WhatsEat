package com.whatseat.database;

import com.whatseat.food.Food;

public abstract class DatabaseTable {

    protected final WhatsEatDatabase database;

    DatabaseTable(WhatsEatDatabase database){
        this.database = database;
    }

}
