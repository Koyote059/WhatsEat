package com.whatseat.food;
import com.whatseat.food.utils.Allergens;
import com.whatseat.food.utils.FoodNutrients;
import com.whatseat.food.utils.FoodType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Food {

    default FoodType getFoodType() {
        FoodType type = FoodType.VEGAN;
        for(Food child: getChildren()){
            FoodType foodType = child.getFoodType();
            if(!foodType.equals(type) && !foodType.equals(FoodType.VEGAN)){
                if(foodType.equals(FoodType.VEGETARIAN)){
                    type=FoodType.VEGETARIAN;
                } else {
                    type=FoodType.OMNIVOROUS;
                    break;
                }
            }
        }
        return type;
    }
    default FoodNutrients getFoodNutrients(){
        float fats=0, carbs=0, prot=0;
        for(Food child: getChildren()){
            FoodNutrients childNutrients = child.getFoodNutrients();
            fats+=childNutrients.getFats();
            carbs+=childNutrients.getCarbs();
            prot+=childNutrients.getProt();
        }
        return new FoodNutrients(fats,carbs,prot);
    }

    default Set<Allergens> getAllergens(){
        Set<Allergens> allergens = new HashSet<>();
        for(Food child: getChildren()){
            Set<Allergens> childAllergens = child.getAllergens();
            allergens.addAll(childAllergens);
        }
        return allergens;
    }

    default int getPrice(){
        int price = 0;
        for(Food child: getChildren()){
            price+=child.getPrice();
        }
        return price;
    }
    List<Food> getChildren();
}
