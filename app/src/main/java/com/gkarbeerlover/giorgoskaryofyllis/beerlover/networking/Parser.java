package com.gkarbeerlover.giorgoskaryofyllis.beerlover.networking;

import com.gkarbeerlover.giorgoskaryofyllis.beerlover.models.Beer;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.models.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static ArrayList<Beer> beersToJson(JSONArray array) throws JSONException {

        /* Array list to hold all BasicRecipe objects */
        ArrayList<Beer> beersArrayList = new ArrayList<>();


        JSONArray drinksArray = array;


        for (int i = 0; i < drinksArray.length(); i++) {
            Beer beer = new Beer();

            JSONObject item = drinksArray.getJSONObject(i);

            JSONObject ingredientsArray = item.getJSONObject("ingredients");
            JSONArray malt = ingredientsArray.getJSONArray("malt");
            JSONArray hops = ingredientsArray.getJSONArray("hops");

            beer.setName(item.getString("name"));
            beer.setTagline(item.getString("tagline"));
            beer.setAuthor(item.getString("contributed_by"));
            beer.setFirstBrewed(item.getString("first_brewed"));
            beer.setImgUrl(item.getString("image_url"));
            beer.setId(item.getInt("id"));
            beer.setDescription(item.getString("description"));
            beer.setFirstBrewed(item.getString("first_brewed"));
            beer.setTips(item.getString("brewers_tips"));

            List<Ingredient> ingredients = new ArrayList<>();


//             Get ingredients
            for (int j = 0; j < malt.length(); j++) {
                Ingredient ingredient = new Ingredient();
                JSONObject ingredientItem = malt.getJSONObject(j);
                ingredient.setName(ingredientItem.getString("name"));
                JSONObject amount = ingredientItem.getJSONObject("amount");
                ingredient.setValue(amount.getString("value"));
                ingredient.setUnit(amount.getString("unit"));
                ingredients.add(ingredient);
            }

            for (int j = 0; j < hops.length(); j++) {
                Ingredient ingredient = new Ingredient();
                JSONObject ingredientItem = hops.getJSONObject(j);
                ingredient.setName(ingredientItem.getString("name"));
                JSONObject amount = ingredientItem.getJSONObject("amount");
                ingredient.setValue(amount.getString("value"));
                ingredient.setUnit(amount.getString("unit"));
                ingredients.add(ingredient);
            }

            JSONArray foods = item.getJSONArray("food_pairing");
            List<String> foodList = new ArrayList<>();
            for(int j = 0; j < foods.length(); j++){
                foodList.add(foods.getString(j));
            }

            beer.setFoodServed(foodList);
            beer.setIngredients(ingredients);
            /*add recipe to the list */
            beersArrayList.add(beer);
        }
        return beersArrayList;
    }
}
