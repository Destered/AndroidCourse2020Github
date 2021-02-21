package com.dester.androidcourse2020github.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dester.androidcourse2020github.core.BaseFragment
import com.dester.androidcourse2020github.databinding.FragmentMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException


class MainScreenFragment : BaseFragment<MainScreenVM>() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainScreenVM(
            lifecycleScope,
            this,
            { name -> navigateToDetailWeather(name) })
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)

        binding.apply {
            lifecycleOwner = this@MainScreenFragment
            vm = viewModel
            rvCityList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvCityList.adapter = viewModel.popularCityAdapter
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location ->
                viewModel.latitude.postValue(location.latitude)
                viewModel.longitude = location.latitude
            }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { cityName ->
                    runBlocking {
                        try {
                            val result = getCityWeather(lifecycleScope, cityName)
                            navigateToDetailWeather(result.name)
                        } catch (he: HttpException) {
                            createSnack(requireView())
                        }
                    }
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        Log.d("Course/FragmentMain", "Set Root")
        return binding.root
    }

    fun navigateToDetailWeather(cityName: String) {
        val action =
            MainScreenFragmentDirections.actionMainScreenFragmentToWeatherDetailedFragment(cityName)
        navigationController.navigate(action)
    }

    fun createSnack(view: View) {
        val snackbar = Snackbar.make(
            view, "Город не найден",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackbar.setActionTextColor(Color.BLACK)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#F6F7FB"))
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView.setTextColor(Color.BLACK)
        textView.textSize = 28f
        snackbar.show()
    }
}