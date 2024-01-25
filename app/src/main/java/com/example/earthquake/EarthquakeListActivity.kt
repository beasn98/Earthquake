package com.example.earthquake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.earthquake.databinding.ActivityEarthquakeListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthquakeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEarthquakeListBinding
    private lateinit var earthquakeList: FeatureCollection
    private lateinit var earthquakeAdapter: EarthquakeAdapter

    companion object {
        val TAG = "EarthquakeListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitHelper.getInstance()
        val earthquakeService = retrofit.create(EarthquakeService::class.java)
        val earthquakeCall = earthquakeService.getEarthquakeDataPastDay()
        earthquakeCall.enqueue(object: Callback<FeatureCollection> {
            override fun onResponse(
                call: Call<FeatureCollection>,
                response: Response<FeatureCollection>
            ) {
                //this is where you get your data
                // this is where you will set up your adapter for recyclerView
                // don't forget a null check before trying to use the data
                // response.body() contains the object in the <> after Response
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<FeatureCollection>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        

        val inputStream = resources.openRawResource(R.raw.earthquakes) //JSON file goes here
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<FeatureCollection>() {}.type // data type of the list.
        val list = gson.fromJson<FeatureCollection>(jsonString, type)
        earthquakeList = list

    }


}