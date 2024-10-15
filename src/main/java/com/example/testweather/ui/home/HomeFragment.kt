package com.example.testweather.ui.home

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testweather.R
import com.example.testweather.databinding.FragmentHomeBinding
import com.example.testweather.domain.entity.CurrentWeatherRS
import com.example.testweather.ui.cities.CitiesListAdapter
import com.example.testweather.ui.cities.CustomClickListener
import com.example.testweather.ui.details.DetailsFragment
import com.example.testweather.util.showErrorDialog
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), CustomClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val citiesNameResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val autocompleteResult =
                    result.data?.let { Autocomplete.getPlaceFromIntent(it).latLng }
                viewModel.fetchWeather(
                    lat = autocompleteResult?.latitude.toString(),
                    lon = autocompleteResult?.longitude.toString()
                )
            }
        }
    private val citiesListAdapter: CitiesListAdapter by lazy {
        CitiesListAdapter(customClickListener = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        bindAdapter()
        observeViewModel()
    }

    private fun bindAdapter() {
        binding.rvCitiesList.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = citiesListAdapter
        }
    }

    private fun initUI() {
        with(binding) {
            btnAddCity.setOnClickListener {
                initAutocomplete()
            }
            tvAddLocation.setOnClickListener {
                initAutocomplete()
            }
        }
    }

    private fun initAutocomplete() {
        if (!Places.isInitialized()) {
            ///google and github alert
            Places.initialize(requireContext(), getString(R.string.api_key).replace("*",""))
        }
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.OVERLAY,
            listOf(Place.Field.NAME, Place.Field.LAT_LNG)
        )
            .build(requireContext())
        citiesNameResultLauncher.launch(intent)
    }

    private fun observeViewModel() {
        viewModel.currentWeather.observe(viewLifecycleOwner) { citiesList ->
            citiesListAdapter.submitList(citiesList.toList())
            buttonsVisibility(citiesList.isEmpty())
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            requireContext().showErrorDialog(error)
        }
    }

    private fun buttonsVisibility(isVisible: Boolean) {
        with(binding) {
            tvAddLocation.isVisible = isVisible
            btnAddCity.isVisible = !isVisible
        }
    }

    private fun showWeatherDetails(item: CurrentWeatherRS?) {
        val dialog = DetailsFragment.newInstance(item)
        dialog.show(parentFragmentManager, null)
    }

    override fun onItemClicked(item: CurrentWeatherRS?) {
        showWeatherDetails(item)
    }

    override fun onDeleteClicked(item: CurrentWeatherRS?) {
        if (item != null) {
            viewModel.removeItem(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}