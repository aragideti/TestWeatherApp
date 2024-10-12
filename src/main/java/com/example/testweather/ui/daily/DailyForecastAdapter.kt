package com.example.testweather.ui.daily

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testweather.R
import com.example.testweather.databinding.ItemDailyBinding
import com.example.testweather.domain.entity.WeatherData
import com.example.testweather.util.DateFormats
import com.example.testweather.util.DateTimeFormatter
import com.example.testweather.util.iconMapper
import com.example.testweather.util.loadImage
import com.example.testweather.util.roundValue

class DailyForecastAdapter(
) : RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {
    private var currentList = emptyList<WeatherData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        return DailyForecastViewHolder.create(parent)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun submitList(data: List<WeatherData>) {
        currentList = data
        notifyDataSetChanged()
    }

    class DailyForecastViewHolder(
        private val binding: ItemDailyBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherData) {
            with(binding) {
                tvDate.text =
                    DateTimeFormatter.format(item.dt, DateFormats.DAYWEEK_DAY_MONTH.format)
                tvConditions.text = item.weather?.first()?.description
                tvTempRange.text = root.context.getString(
                    R.string.temp_range,
                    item.temp?.max?.roundValue(), item.temp?.min?.roundValue(),
                )
                ivImageWeather.apply {
                    Glide.with(context)
                        .load(
                            context.loadImage(
                                item.weather?.first()?.icon?.iconMapper().orEmpty()
                            )
                        )
                        .into(this)
                }
            }
        }

        companion object {

            fun create(
                parent: ViewGroup,
            ): DailyForecastViewHolder {
                return DailyForecastViewHolder(getViewBinding(parent))
            }

            private fun getViewBinding(parent: ViewGroup): ItemDailyBinding {
                return ItemDailyBinding.inflate(
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                    parent,
                    false
                )
            }
        }
    }
}