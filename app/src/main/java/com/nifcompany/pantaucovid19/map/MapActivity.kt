package com.nifcompany.pantaucovid19.map

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.nifcompany.pantaucovid19.R
import com.nifcompany.pantaucovid19.core.data.Resource
import com.nifcompany.pantaucovid19.core.domain.model.Province
import com.nifcompany.pantaucovid19.databinding.ActivityMapBinding
import com.nifcompany.pantaucovid19.provinceDetail.ProvinceDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapActivity : AppCompatActivity() {
    private val mapsViewModel: MapsViewModel by viewModel()
    private lateinit var binding: ActivityMapBinding
    private lateinit var mapboxMap: MapboxMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Tourism Map"
        binding.MapView.onCreate(savedInstanceState)
        binding.MapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            getProvinceData()
        }
    }

    private fun getProvinceData() {
        mapsViewModel.covid.observe(this, { covid ->
            if (covid != null) {
                when (covid) {
                    is Resource.Loading -> binding.PbMap.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.PbMap.visibility = View.GONE
                        showMarker(covid.data)
                    }
                    is Resource.Error -> {
                        binding.PbMap.visibility = View.GONE
                        binding.TvError.visibility = View.VISIBLE
                        binding.TvError.text = covid.message
                    }
                }
            }
        })
    }

    private fun showMarker(dataTourism: List<Province>?) {
        mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
            style.addImage(ICON_ID, BitmapFactory.decodeResource(resources, R.drawable.mapbox_marker_icon_default))
            val latLngBoundsBuilder = LatLngBounds.Builder()
            val symbolManager = SymbolManager(binding.MapView, mapboxMap, style)
            symbolManager.iconAllowOverlap = true
            val options = ArrayList<SymbolOptions>()
            dataTourism?.forEach { data ->
                latLngBoundsBuilder.include(LatLng(data.lat, data.lon))
                options.add(
                    SymbolOptions()
                        .withLatLng(LatLng(data.lat, data.lon))
                        .withIconImage(ICON_ID)
                        .withData(Gson().toJsonTree(data))
                )
            }
            symbolManager.create(options)
            val latLngBounds = latLngBoundsBuilder.build()
            mapboxMap.easeCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50), 5000)
            symbolManager.addClickListener { symbol ->
                val data = Gson().fromJson(symbol.data, Province::class.java)
                val intent = Intent(this, ProvinceDetailActivity::class.java)
                intent.putExtra(ProvinceDetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        }
    }

    companion object {
        private const val ICON_ID = "ICON_ID"
    }

    override fun onStart() {
        super.onStart()
        binding.MapView.onStart()
    }
    override fun onResume() {
        super.onResume()
        binding.MapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        binding.MapView.onPause()
    }
    override fun onStop() {
        super.onStop()
        binding.MapView.onStop()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.MapView.onSaveInstanceState(outState)
    }
    override fun onDestroy() {
        super.onDestroy()
        binding.MapView.onDestroy()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        binding.MapView.onLowMemory()
    }
}