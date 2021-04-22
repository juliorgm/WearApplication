package com.bootcamp.watch

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.databinding.ActivityListMealBinding

class MealListActivity : Activity(), MealListAdapter.Callback {
  private var adapter: MealListAdapter? = null
  lateinit var binding: ActivityListMealBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityListMealBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val meals = MealStore.fetchMeals(this)
    adapter = MealListAdapter(meals, this)
    binding.list.adapter = adapter
    binding.list.layoutManager = LinearLayoutManager(this)
  }

  override fun mealClicked(meal: Meal) {}
}
