package com.example.earthquake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earthquake.databinding.ActivityEarthquakeListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EarthquakeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEarthquakeListBinding
    private lateinit var earthquakeList: List<Earthquake>
    private lateinit var earthquakeAdapter: EarthquakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = resources.openRawResource(R.raw.heroes) //JSON file goes here
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<Earthquake>>() {}.type // data type of the list.
        val list = gson.fromJson<List<Earthquake>>(jsonString, type)
        earthquakeList = list
    }


}