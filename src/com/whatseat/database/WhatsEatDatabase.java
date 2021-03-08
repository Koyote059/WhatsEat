package com.whatseat.database;

import java.util.HashMap;
import java.util.Map;

public class WhatsEatDatabase {

    private static final Map<String,WhatsEatDatabase> databaseMap = new HashMap<>();

    public static WhatsEatDatabase getInstance(String name){
        if(!databaseMap.containsKey(name)) databaseMap.put(name, new WhatsEatDatabase(name));
        return databaseMap.get(name);
    }

    /* ---------------------------------------------------------------------------------------------------- */

    protected final String name;
    private final Map<DatabaseTableType,DatabaseTable> tables = new HashMap<>();

    public WhatsEatDatabase(String name) {
        // Todo -> Create database SQL statement
        this.name = name;
    }

    public DatabaseTable getTable(DatabaseTableType tableType){
        switch (tableType){
            case DIETS -> {
                if(!tables.containsKey(DatabaseTableType.DIETS)) tables.put(DatabaseTableType.DIETS,new DietsTable(this));
                return tables.get(DatabaseTableType.DIETS);
            }
            case MEALS -> {
                if(!tables.containsKey(DatabaseTableType.MEALS)) tables.put(DatabaseTableType.MEALS,new MealsTable(this));
                return tables.get(DatabaseTableType.MEALS);
            }
            case INGREDIENTS -> {
                if(!tables.containsKey(DatabaseTableType.INGREDIENTS)) tables.put(DatabaseTableType.INGREDIENTS,new MealsTable(this));
                return tables.get(DatabaseTableType.INGREDIENTS);
            }
            default -> {
                return null;
            }
        }
    }

    public enum DatabaseTableType {
        DIETS, MEALS, INGREDIENTS
    }
}
