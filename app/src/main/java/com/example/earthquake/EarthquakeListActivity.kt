package com.example.earthquake

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquake.databinding.ActivityEarthquakeListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthquakeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEarthquakeListBinding
    private lateinit var earthquakeAdapter: EarthquakeAdapter
    private lateinit var earthquakeList: List<Feature>

    companion object {
        val TAG = "EarthquakeListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Earthquake App"
        supportActionBar?.subtitle = "USGS All Earthquakes, Past Day"

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
                if (response.body() != null) {
                    earthquakeList = response.body()!!.features.filter { it.properties.mag > 1.0 }
                    earthquakeAdapter = EarthquakeAdapter(earthquakeList.sortedWith(
                        compareBy<Feature> {-it.properties.mag}
                            .thenByDescending {it.properties.time}
                    ))
                    refresh()
                }
                else {
                    Log.d(TAG, "onResponse: feature is null")
                }

            }

            override fun onFailure(call: Call<FeatureCollection>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.earthquakelist_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItem_menu_sortMag -> {
                earthquakeAdapter = EarthquakeAdapter(earthquakeList.sortedWith(
                    compareBy<Feature> {-it.properties.mag}
                        .thenByDescending {it.properties.time}
                ))
                refresh()
                true
            }
            R.id.menuItem_menu_sortRecent -> {
                earthquakeAdapter = EarthquakeAdapter(earthquakeList.sortedBy {-it.properties.time})
                refresh()
                true
            }
            R.id.menuItem_menu_legend -> {
                val builder = AlertDialog.Builder(this)

                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateDialog(id: Int, args: Bundle?): Dialog? {
        return super.onCreateDialog(id, args)
    }
    fun refresh() {
        binding.recyclerViewEarthquakeListEarthquake.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEarthquakeListEarthquake.adapter = earthquakeAdapter
    }


}