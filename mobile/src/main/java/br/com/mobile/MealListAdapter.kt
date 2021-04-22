package com.bootcamp.watch


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mobile.R
import br.com.shared.Meal

class MealListAdapter(
        private val meals: MutableList<Meal>,
        private val callback: Callback?
) : RecyclerView.Adapter<MealListAdapter.MealViewHolder>() {

  override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
    val meal = meals[position]
    holder.title.text = meal.title
    holder.ingredients.text = meal.ingredients.joinToString(separator = ", ")
    holder.calories.text = meal.calories.toString()
    holder.star.visibility = if (meal.favorited) View.VISIBLE else View.INVISIBLE
    holder.itemView.setOnClickListener {
      callback?.mealClicked(meal)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_meal, parent, false)
    return MealViewHolder(view)
  }

  override fun getItemCount() = meals.size

  fun updateMeal(meal: Meal) {
    for ((index, value) in meals.withIndex()) {
      if (value.title == meal.title) {
        meals[index] = meal
      }
    }

    notifyDataSetChanged()
  }

  inner class MealViewHolder(view: View) : RecyclerView.ViewHolder(view){
     val title = view.findViewById<TextView>(R.id.title)
     val ingredients = view.findViewById<TextView>(R.id.ingredients)
     val calories = view.findViewById<TextView>(R.id.calories)
     val star = view.findViewById<ImageView>(R.id.star)
  }

  interface Callback {
    fun mealClicked(meal: Meal)
  }
}