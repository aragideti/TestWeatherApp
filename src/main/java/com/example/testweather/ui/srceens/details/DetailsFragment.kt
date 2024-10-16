package com.example.testweather.ui.srceens.details

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testweather.R
import com.example.testweather.databinding.FragmentDetailsBinding
import com.example.testweather.domain.entity.CurrentWeatherRS
import com.example.testweather.domain.entity.DailyForecastRS
import com.example.testweather.ui.daily.DailyForecastAdapter
import com.example.testweather.ui.hourly.HourlyForecastAdapter
import com.example.testweather.util.fullScreenDialogwithBackground
import com.example.testweather.util.iconMapper
import com.example.testweather.util.loadImage
import com.example.testweather.util.roundValue
import com.example.testweather.util.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : DialogFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private val dailyForecastAdapter: DailyForecastAdapter by lazy {
        DailyForecastAdapter()
    }
    private val hourlyForecastAdapter: HourlyForecastAdapter by lazy {
        HourlyForecastAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(getCurrentWeather())
        bindDailyAdapter()
        bindHourlyAdapter()
        observeViewModel()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return this@DetailsFragment.fullScreenDialogwithBackground(FrameLayout(requireActivity()))
    }

    private fun initUI(item: CurrentWeatherRS) {
        initCurrentWeather(item)
        fetchForecast(item)
    }

    private fun bindDailyAdapter() {
        binding.rvDaily.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            overScrollMode = View.OVER_SCROLL_NEVER
            itemAnimator = null
            adapter = dailyForecastAdapter
        }
    }

    private fun bindHourlyAdapter() {
        binding.rvHourly.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            overScrollMode = View.OVER_SCROLL_NEVER
            itemAnimator = null
            adapter = hourlyForecastAdapter
        }
    }

    private fun initCurrentWeather(item: CurrentWeatherRS) {
        with(binding) {
            tvLocation.text = "${item.name}, ${item.sys?.country}"
            tvCurrentTemp.text = getString(R.string.current_temp, item.main?.temp?.roundValue())
            tvDescription.text = item.weather.first().description
            ivImageWeather.apply {
                Glide.with(context)
                    .load(context.loadImage(item.weather.first().icon?.iconMapper().orEmpty()))
                    .into(this)
            }
            tvHumidityValue.text = getString(R.string.humidity_val, item.main?.humidity.toString())
            tvPressureValue.text = getString(R.string.pressure_val, item.main?.pressure.toString())
            tvWindValue.text = getString(R.string.wind_val, item.wind?.speed.toString())
            tvCloudCoverValue.text =
                getString(R.string.cloud_cover_val, item.clouds?.all.toString())

        }
    }

    private fun addMinMaxTemp(weather: DailyForecastRS) {
        with(binding) {
            tvTempMax.text = getString(
                R.string.temp_max,
                weather.list?.first()?.temp?.max?.roundValue()
            )
            tvTempMin.text = getString(
                R.string.temp_min,
                weather.list?.first()?.temp?.min?.roundValue()
            )
            tvFeelsLike.text = getString(
                R.string.fells_like,
                weather.list?.first()?.feelsLike?.day?.roundValue()
            )
        }
    }

    private fun fetchForecast(item: CurrentWeatherRS) {
        viewModel.fetchDailyForecast(lat = item.coord.lat, lon = item.coord.lon)
        viewModel.fetchHourlyForecast(lat = item.coord.lat, lon = item.coord.lon)
    }

    private fun observeViewModel() {
        with(viewModel) {
            dailyForecast.observe(viewLifecycleOwner) { weather ->
                addMinMaxTemp(weather)
                dailyForecastAdapter.submitList(weather.list.orEmpty())
            }
            hourlyForecast.observe(viewLifecycleOwner) { weather ->
                hourlyForecastAdapter.submitList(weather.list.orEmpty())
            }
            error.observe(viewLifecycleOwner) { error ->
                requireContext().showErrorDialog(error)
            }
        }
    }

    private fun getCurrentWeather(): CurrentWeatherRS {
        val weather: CurrentWeatherRS? = requireArguments().getParcelable(CURRENT_WEATHER)
        requireNotNull(weather)
        return weather
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

    companion object {
        private const val CURRENT_WEATHER = "CURRENT_WEATHER"

        fun newInstance(
            currentWeather: CurrentWeatherRS?,
        ): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(CURRENT_WEATHER to currentWeather)
            }
        }
    }
}