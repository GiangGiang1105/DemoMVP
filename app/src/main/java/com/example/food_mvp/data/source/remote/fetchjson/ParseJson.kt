package com.example.food_mvp.data.source.remote.fetchjson

import com.example.food_mvp.data.model.Food
import com.example.food_mvp.data.model.FoodEntry
import org.json.JSONObject

class ParseJson {

    fun foodParseJson(jsonObject: JSONObject): Food {
        return Food(
            strMeal = jsonObject.getString(FoodEntry.CATEGORY_MEAL),
            strMealThumb = jsonObject.getString(FoodEntry.CATEGORY_THUMB),
            idMeal = jsonObject.getString(FoodEntry.ID_MEAL)
        )
    }
}
