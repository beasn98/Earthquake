package com.example.earthquake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.earthquake.databinding.ActivityEarthquakeListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EarthquakeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEarthquakeListBinding
    private lateinit var earthquakeList: List<FeatureCollection>
    private lateinit var earthquakeAdapter: EarthquakeAdapter

    companion object {
        val TAG = "EarthquakeListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = resources.openRawResource(R.raw.earthquakes) //JSON file goes here
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<FeatureCollection>>() {}.type // data type of the list.
        val list = gson.fromJson<List<FeatureCollection>>(jsonString, type)
        earthquakeList = list

        Log.d(TAG, "onCreate: $earthquakeList")
    }


}