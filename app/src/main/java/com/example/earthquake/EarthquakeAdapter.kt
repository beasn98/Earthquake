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
        val earthquake = earthquakesList[position]
        viewHolder.textViewMagnitude.text = String.format("%.1f", earthquake.properties.mag)
        viewHolder.textViewLocation.text = earthquake.properties.place
        viewHolder.textViewTime.text = Date(earthquake.properties.time).toString()

        when (earthquake.properties.mag) {
            //small
            in 1.0..2.5 -> {
                viewHolder.textViewMagnitude.setTextColor(context.resources.getColor(R.color.blue))
                viewHolder.textViewMagnitude.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0)
            }
            //moderate
            in 2.51..4.5 -> {
                viewHolder.textViewMagnitude.setTextColor(context.resources.getColor(R.color.orange))
                viewHolder.textViewMagnitude.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0)
            }
            //large
            in 4.51..6.5 -> {
                viewHolder.textViewMagnitude.setTextColor(context.resources.getColor(R.color.red))
                viewHolder.textViewMagnitude.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.baseline_warning_24,0,0,0)
            }
            //significant
            else -> {
                viewHolder.textViewMagnitude.setTextColor(context.resources.getColor(R.color.purple))
                viewHolder.textViewMagnitude.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.baseline_warning_amber_24,0,0,0)
            }
        }

        viewHolder.layout.setOnClickListener {
            val earthquakeIntent = Intent(context, EarthquakeMapActivity::class.java)
            earthquakeIntent.putExtra(EXTRA_EARTHQUAKE, earthquake)
            context.startActivity(earthquakeIntent)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = earthquakesList.size

}
