package com.example.earthquake

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class EarthquakeAdapter(private val earthquakesList: Array<String>) :
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

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.textView.text = earthquakesList[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = earthquakesList.size

}
