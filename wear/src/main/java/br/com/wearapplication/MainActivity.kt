package br.com.wearapplication

import android.app.Activity
import android.os.Bundle
import br.com.shared.Meal
import br.com.wearapplication.databinding.ActivityMainBinding
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson

class MainActivity : Activity(), GoogleApiClient.ConnectionCallbacks {

    private lateinit var client: GoogleApiClient
    private var currentMeal: Meal? = null
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onConnected(p0: Bundle?) {
        Wearable.MessageApi.addListener(client) { messageEvent ->
            currentMeal = Gson().fromJson(String(messageEvent.data), Meal::class.java)
            updateView()
        }
    }

    private fun updateView() {
        currentMeal?.let {
            binding.mealTitle.text = it.title
            binding.caloriesWear.text = "Calorias: ${it.calories}"
            binding.ingredientsWear.text = it.ingredients.joinToString { ", " }
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }
}