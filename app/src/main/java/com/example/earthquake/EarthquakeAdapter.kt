package com.example.earthquake

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.util.Date


class EarthquakeAdapter(var earthquakesList: List<Feature>) :
    RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewMagnitude: TextView
        val textViewLocation: TextView
        val textViewTime: TextView
        val layout: ConstraintLayout
        init {
            // Define click listener for the ViewHolder's View
            textViewMagnitude = view.findViewById(R.id.textView_eartquakeItem_magnitude)
            textViewLocation = view.findViewById(R.id.textView_earthquakeItem_location)
            textViewTime = view.findViewById(R.id.textView_earthquakeItem_time)
            layout = view.findViewById(R.id.layout_itemEarthquake)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_earthquake, viewGroup, false)

        return ViewHolder(view)
    }

    companion object {
        val EXTRA_EARTHQUAKE = "earthquake"
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val context = viewHolder.layout.context
        viewHolder.textViewMagnitude.text = String.format("%.2f", earthquakesList[position].properties.mag)
        viewHolder.textViewLocation.text = earthquakesList[position].properties.place
        viewHolder.textViewTime.text = Date(earthquakesList[position].properties.time).toString()

        viewHolder.layout.setOnClickListener {
            val earthquakeIntent = Intent(context, EarthquakeMapActivity::class.java)
            earthquakeIntent.putExtra(EXTRA_EARTHQUAKE, earthquakesList[position])
            context.startActivity(earthquakeIntent)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = earthquakesList.size

}
