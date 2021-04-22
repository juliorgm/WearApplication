package com.bootcamp.watch

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mobile.databinding.ActivityListMealBinding
import br.com.shared.Meal
import com.google.android.gms.common.api.GoogleApiActivity
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.*
import com.google.gson.Gson

class MealListActivity : Activity(), MealListAdapter.Callback, GoogleApiClient.ConnectionCallbacks{
  private var adapter: MealListAdapter? = null
  private lateinit var client: GoogleApiClient
  private var connectedNode: List<Node>? = null


  lateinit var binding: ActivityListMealBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityListMealBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val meals = MealStore.fetchMeals(this)
    adapter = MealListAdapter(meals, this)
    binding.list.adapter = adapter
    binding.list.layoutManager = LinearLayoutManager(this)

    client = GoogleApiClient.Builder(this)
      .addApi(Wearable.API)
      .addConnectionCallbacks(this)
      .build()
    client.connect()


  }

  override fun mealClicked(meal: Meal) {
    val gson = Gson()
    connectedNode?.forEach{ node ->
      val bytes = gson.toJson(meal).toByteArray()
      Wearable.MessageApi.sendMessage(client, node.id, "/meal", bytes)
    }
  }

  override fun onConnected(p0: Bundle?) {
    Wearable.NodeApi.getConnectedNodes(client).setResultCallback{
      connectedNode = it.nodes
    }
  }

  override fun onConnectionSuspended(p0: Int) {
    connectedNode = null
  }
}
