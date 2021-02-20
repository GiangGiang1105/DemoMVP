package com.example.food_mvp.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.food_mvp.R
import com.example.food_mvp.data.model.Food
import com.example.food_mvp.data.source.FoodRepository
import com.example.food_mvp.screen.main.adapter.MainAdapter
import com.example.food_mvp.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemRecyclerViewClickListener<Food>, MainContract.View {

    private val adapter: MainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        recyclerViewFood.setHasFixedSize(true)
        recyclerViewFood.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)
    }

    private fun initData() {
        val presenter = MainPresenter(FoodRepository.instance)
        presenter.setView(this)
        presenter.onStart()
    }

    override fun onItemClickListener(item: Food?) {
        Toast.makeText(this, item?.idMeal, Toast.LENGTH_LONG).show()
    }

    override fun onGetFoodsSuccess(foods: MutableList<Food>) {
        adapter.updateData(foods)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
    }
}
