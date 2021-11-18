package com.ankur.crypto.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankur.crypto.R
import com.ankur.crypto.data.Data


class CryptoAdapter(private val dataSet: List<Data>?) :
    RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById<TextView>(R.id.Crypto_Name)
        val shortname: TextView = view.findViewById<TextView>(R.id.Crypto_Short_Name)
        val price: TextView = view.findViewById<TextView>(R.id.price)
        val change: TextView = view.findViewById<TextView>(R.id.day_change)
        val image: ImageView = view.findViewById<ImageView>(R.id.logo)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.crypto_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if (dataSet != null) {
            viewHolder.name.text = dataSet[position].name
            viewHolder.shortname.text = dataSet[position].symbol
            viewHolder.price.text = dataSet[position].metrics.market_data.price_usd.toString()
            viewHolder.change.text =
                dataSet[position].metrics.market_data.percent_change_usd_last_1_hour.toString()
            viewHolder.image.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet?.size ?: 0

}