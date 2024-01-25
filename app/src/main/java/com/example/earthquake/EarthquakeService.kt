package com.example.earthquake

import retrofit2.Call
import retrofit2.http.GET

interface EarthquakeService {
    //where you list out the different endpoints on the API
    //function returns Call<type> where type is the fata returned in the json
    //in the @GET("blah"), "blah" is the path to the file endpoint

    @GET("all_day.geojson")
    fun getEarthquakeDataPastDay() : Call<FeatureCollection>
}