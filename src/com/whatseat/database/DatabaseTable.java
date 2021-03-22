package com.whatseat.database;

import com.whatseat.food.Food;

import javax.sql.rowset.CachedRowSet;

public abstract class DatabaseTable {

    protected final WhatsEatDatabase database;

    protected void executeQueries(String... queries){
        database.executeQueries(queries);
    }

     protected CachedRowSet executeQueryWithResult(String query){
        return database.executeQueryWithResult(query);
    }

    DatabaseTable(WhatsEatDatabase database){
        this.database = database;
    }

}
