package br.com.wearapplication

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import androidx.wear.widget.WearableRecyclerView

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()
    }
}