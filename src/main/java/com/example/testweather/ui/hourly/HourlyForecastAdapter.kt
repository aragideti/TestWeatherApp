package com.example.testweather.ui.hourly

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
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

class HourlyForecastAdapter(
) : RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastAdapter>() {
    private var currentList = emptyList<WeatherItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastAdapter {
        return HourlyForecastAdapter.create(parent)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: HourlyForecastAdapter, position: Int) {
        holder.bind(currentList[position])
    }

    fun submitList(data: List<WeatherItem>) {
        currentList = data
        notifyDataSetChanged()
    }

    class HourlyForecastAdapter(
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
            ): HourlyForecastAdapter {
                return HourlyForecastAdapter(getViewBinding(parent))
            }

            private fun getViewBinding(parent: ViewGroup): ItemHourlyBinding {
                return ItemHourlyBinding.inflate(
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                    parent,
                    false
                )
            }
        }
    }
}