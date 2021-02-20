package com.example.food_mvp.screen.main

import com.example.food_mvp.data.model.Food
import com.example.food_mvp.utils.BasePresenter

interface MainContract {
    /**
     * View
     */
    interface View {
        fun onGetFoodsSuccess(foods: MutableList<Food>)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getFoods()
    }

}
