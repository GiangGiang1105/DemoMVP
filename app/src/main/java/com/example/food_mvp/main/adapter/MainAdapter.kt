package com.example.food_mvp.screen.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_mvp.R
import com.example.food_mvp.data.model.Food
import com.example.food_mvp.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.item_layout_food.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder?>(){

    private val foods = mutableListOf<Food>()
    private var onItemClickListener: OnItemRecyclerViewClickListener<Food>? = null

    fun updateData(foods: MutableList<Food>?) {
        foods?.let {
            this.foods.apply {
                clear()
                addAll(foods)
            }
            notifyDataSetChanged()
        }
    }

    fun registerItemRecyclerViewClickListener(
        onItemRecyclerViewClickListener: OnItemRecyclerViewClickListener<Food>?) {
        onItemClickListener = onItemRecyclerViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_food, parent, false)
        return ViewHolder(view, onItemClickListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewData(foods[position])
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    inner class ViewHolder(itemView: View?, private val  itemListener: OnItemRecyclerViewClickListener<Food>?) :
        RecyclerView.ViewHolder(itemView!!), View.OnClickListener {

        private var listener: OnItemRecyclerViewClickListener<Food>? = null

        fun bindViewData(food: Food) {
            listener = itemListener
            itemView.titleMeal.text = food.strMeal
            getImageCircle(food)
        }

        private fun getImageCircle(food: Food){
            Glide.with(itemView.context)
                .load(food.strMealThumb)
                .into(itemView.imageFood)
        }
        override fun onClick(v: View?) {
            listener?.onItemClickListener(foods[adapterPosition])
        }
    }
}
