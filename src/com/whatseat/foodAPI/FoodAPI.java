package com.whatseat.foodAPI;

import com.whatseat.food.FoodNutrients;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FoodAPI {
    private static HttpURLConnection connection;
    private final static String apiId = "f7810269";
    private final static String apiKey = "025dbfc1650e47e090dbc9af00382e7a";
    private final static String getLink = "https://api.edamam.com/api/food-database/v2/parser?app_id="+apiId+"&app_key="+apiKey+"&category=generic-foods&nutrition-type=logging";

    public static JSONObject getFoodByName(String name) {
        String response = null;
        try {
            connection = (HttpURLConnection) new URL(getLink + "&ingr=" + name).openConnection();
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {
                response = "";
                Scanner scanner = new Scanner(connection.getInputStream());
                while (scanner.hasNextLine()) {
                    response += scanner.nextLine();
                    response += "\n";
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            return new JSONObject(response).getJSONArray("parsed").getJSONObject(0).getJSONObject("food");
        }catch (JSONException e){
            return new JSONObject(response).getJSONArray("hints").getJSONObject(0).getJSONObject("food");
        }
    }

    public static FoodNutrients getNutrientsOf(String name) {
        JSONObject jsonObject = getFoodByName(name);
        float carbs, fats, prot;
        carbs = jsonObject.getJSONObject("nutrients").getFloat("CHOCDF");
        fats = jsonObject.getJSONObject("nutrients").getFloat("FAT");
        prot = jsonObject.getJSONObject("nutrients").getFloat("PROCNT");
        FoodNutrients nutrients = new FoodNutrients(fats, carbs, prot);

        return nutrients;
    }
}
