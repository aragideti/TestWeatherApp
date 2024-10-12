package com.example.testweather.ui.cities

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testweather.R
import com.example.testweather.databinding.ItemCityBinding
import com.example.testweather.domain.entity.CurrentWeatherRS
import com.example.testweather.util.DateFormats
import com.example.testweather.util.DateTimeFormatter
import com.example.testweather.util.iconMapper
import com.example.testweather.util.loadImage
import com.example.testweather.util.roundValue

class CitiesListAdapter(
    private val customClickListener: CustomClickListener
) : RecyclerView.Adapter<CitiesListAdapter.CitiesListViewHolder>() {
    private var currentList = emptyList<CurrentWeatherRS>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesListViewHolder {
        return CitiesListViewHolder.create(parent, customClickListener)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: CitiesListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun submitList(data: List<CurrentWeatherRS>) {
        currentList = data
        notifyDataSetChanged()
    }

    class CitiesListViewHolder(
        private val binding: ItemCityBinding,
        private val customClickListener: CustomClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        private var item: CurrentWeatherRS? = null

        init {
            with(binding) {
                root.setOnClickListener {
                    customClickListener.onItemClicked(item)
                }
                this.ivDelete.setOnClickListener {
                    customClickListener.onDeleteClicked(item)
                }
            }
        }

        fun bind(item: CurrentWeatherRS) {
            this.item = item
            with(binding) {
                tvTitle.text = item.name
                tvConditions.text = item.weather.first().description
                tvCurrentTemp.text = root.context.getString(
                    R.string.current_temp,
                    item.main?.temp?.roundValue()
                )
                tvUpdatedAt.text =
                    DateTimeFormatter.format(item.dt, DateFormats.DAYWEEK_DAY_MONTH_TIME.format)
                ivImageWeather.apply {
                    Glide.with(context)
                        .load(context.loadImage(item.weather.first().icon?.iconMapper().orEmpty()))
                        .into(this)
                }
            }
        }

        companion object {

            fun create(
                parent: ViewGroup,
                listener: CustomClickListener
            ): CitiesListViewHolder {
                return CitiesListViewHolder(getViewBinding(parent), listener)
            }

            private fun getViewBinding(parent: ViewGroup): ItemCityBinding {
                return ItemCityBinding.inflate(
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                    parent,
                    false
                )
            }
        }
    }
}