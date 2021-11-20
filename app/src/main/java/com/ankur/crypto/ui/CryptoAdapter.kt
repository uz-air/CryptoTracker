package com.ankur.crypto.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ankur.crypto.R
import com.ankur.crypto.data.Data
import com.ankur.crypto.databinding.CryptoRowItemBinding
import java.text.DecimalFormat
import java.text.NumberFormat

import com.ankur.crypto.openLinkInBrowser


class CryptoAdapter(private val dataSet: List<Data>?) :
    RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CryptoRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataSet: Data) {
            val formatter: NumberFormat = DecimalFormat("#,##0.##")
            val formatter2: NumberFormat = DecimalFormat("##.##")
            val priceVal = "$" + formatter.format(dataSet.metrics.market_data.price_usd).toString()
            binding.CryptoName.text = dataSet.name
            binding.CryptoShortName.text = dataSet.symbol
            binding.price.text = priceVal
            binding.dayChange.text =
                formatter2.format(dataSet.metrics.market_data.percent_change_usd_last_1_hour)
                    .toString().removePrefix("-") + "%"
            if (dataSet.metrics.market_data.percent_change_usd_last_1_hour.toString()
                    .contains("-")
            ) {
                binding.dayChange.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_down,
                    0,
                    0,
                    0
                )
                binding.dayChange.setTextColor(
                    ContextCompat.getColor(
                        binding.dayChange.context,
                        R.color.red
                    )
                )
            } else {
                binding.dayChange.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_up, 0, 0, 0)
                binding.dayChange.setTextColor(
                    ContextCompat.getColor(
                        binding.dayChange.context,
                        R.color.green
                    )
                )
            }
            binding.layout.setOnClickListener {
                val url = it.context.getString(R.string.google_url) + dataSet.symbol
                url.openLinkInBrowser(it.context)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            CryptoRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


        if (dataSet != null) {
            viewHolder.bind(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0

}