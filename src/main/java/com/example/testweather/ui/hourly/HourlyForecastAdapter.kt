package com.example.testweather.ui.hourly

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testweather.R
import com.example.testweather.databinding.ItemHourlyBinding
import com.example.testweather.domain.entity.WeatherItem
import com.example.testweather.util.DateFormats
import com.example.testweather.util.DateTimeFormatter
import com.example.testweather.util.iconMapper
import com.example.testweather.util.loadImage
import com.example.testweather.util.roundValue

class HourlyForecastAdapter: ListAdapter<WeatherItem, HourlyForecastAdapter.HourlyViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder =
        HourlyViewHolder.create(parent)

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class HourlyViewHolder(
        private val binding: ItemHourlyBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherItem) {
            with(binding) {
                tvDate.text =
                    DateTimeFormatter.format(item.dt, DateFormats.DAYWEEK_DAY_MONTH_HOURS.format)
                tvConditions.text = item.weather?.first()?.description
                tvTemp.text =
                    root.context.getString(R.string.current_temp, item.main?.temp?.roundValue())
                ivImageWeather.apply {
                    Glide.with(context)
                        .load(context.loadImage(item.weather?.first()?.icon?.iconMapper().orEmpty()))
                        .into(this)
                }
            }
        }

        companion object {

            fun create(
                parent: ViewGroup,
            ): HourlyViewHolder = HourlyViewHolder(getViewBinding(parent))

            private fun getViewBinding(parent: ViewGroup): ItemHourlyBinding {
                return ItemHourlyBinding.inflate(
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                    parent,
                    false
                )
            }
        }
    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<WeatherItem>() {

            override fun areItemsTheSame(
                oldItem: WeatherItem,
                newItem: WeatherItem
            ): Boolean = oldItem.dt == newItem.dt

            override fun areContentsTheSame(
                oldItem: WeatherItem,
                newItem: WeatherItem
            ): Boolean = oldItem == newItem
        }
    }
}