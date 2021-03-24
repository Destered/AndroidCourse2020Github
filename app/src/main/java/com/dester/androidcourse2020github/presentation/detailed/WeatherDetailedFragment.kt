package com.dester.androidcourse2020github.presentation.detailed

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.databinding.FragmentDetailedWeatherBinding
import com.dester.androidcourse2020github.presentation.core.BaseFragment

class WeatherDetailedFragment : BaseFragment<WeatherVM>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = arguments?.getString("cityName") ?: ""
        viewModel = WeatherVM(
            this,
            lifecycleScope,
            name,
            { name -> getDrawableId(name) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailedWeatherBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@WeatherDetailedFragment
            vm = viewModel
        }
        return binding.root
    }

    fun getDrawableId(name: Int): Drawable {
        if (name == 1) {
            return requireContext().resources.getDrawable(R.drawable.hot_transparent_gradient)
        } else {
            return requireContext().resources.getDrawable(R.drawable.cold_transparent_gradient)
        }
    }
}