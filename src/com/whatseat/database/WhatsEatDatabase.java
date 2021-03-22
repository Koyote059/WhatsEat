package com.whatseat.database;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
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
    protected String url;
    private final Map<DatabaseTableType,DatabaseTable> tables = new HashMap<>();

    public WhatsEatDatabase(String name) {
        // Todo -> Create database SQL statement
        this.name = name;
        url = name;
    }

    public void setUrl(String url){

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

    public void executeQueries(String[] queries) {
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();
            for(String query: queries){
                statement.execute(query);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    CachedRowSet executeQueryWithResult(String query){  // Todo wrap Exception
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet cachedRowSet = factory.createCachedRowSet();
            cachedRowSet.populate(resultSet);
            return cachedRowSet;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public enum DatabaseTableType {
        DIETS, MEALS, INGREDIENTS
    }
}
