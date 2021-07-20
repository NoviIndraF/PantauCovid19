package com.nifcompany.pantaucovid19.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nifcompany.pantaucovid19.core.domain.model.Indonesia
import com.nifcompany.pantaucovid19.databinding.ActivityMainBinding
import com.nifcompany.pantaucovid19.homeDetail.HomeDetailActivity
import com.nifcompany.pantaucovid19.homeDetail.HomeDetailActivity.Companion.EXTRA_DATA
import com.nifcompany.pantaucovid19.provinceDetail.ProvinceDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provinceAdapter = ProvinceAdapter()

        provinceAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, ProvinceDetailActivity::class.java)
            intent.putExtra(ProvinceDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        binding.FabMainSave.setOnClickListener {
            val uri = Uri.parse("pantaucovid19://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        Log.e("HomeActivity, Data Indonesia 1 : ", homeViewModel.dataIndonesia.observe(this,{indonesia-> indonesia.data.toString()}).toString())
        homeViewModel.dataIndonesia.observe(this, { indonesia ->
            Log.e("HomeActivity, Data Indonesia 1 : ", indonesia.data.toString())

            if (indonesia != null) {
                when (indonesia) {
                    is com.nifcompany.pantaucovid19.core.data.Resource.Loading -> binding.PbMain.visibility = View.VISIBLE
                    is com.nifcompany.pantaucovid19.core.data.Resource.Success -> {
                        binding.PbMain.visibility = View.GONE
                        Log.e("HomeActivity, Data Indonesia 1 : ", indonesia.data.toString())
                        setBindingMain(indonesia.data)
                    }
                    is com.nifcompany.pantaucovid19.core.data.Resource.Error -> {
                        binding.PbMain.visibility = View.GONE
                    }
                }
            }
        })

        homeViewModel.dataProvince.observe(this, { province ->
            if (province != null) {
                when (province) {
                    is com.nifcompany.pantaucovid19.core.data.Resource.Loading -> {
                        binding.PbMain.visibility = View.VISIBLE
                        binding.ContentCardMain.visibility = View.INVISIBLE}
                    is com.nifcompany.pantaucovid19.core.data.Resource.Success -> {
                        binding.PbMain.visibility = View.GONE
                        binding.ContentCardMain.visibility = View.VISIBLE
                        Log.e("HomeActivity, Data Province 2 : ", province.data.toString())

                        provinceAdapter.setData(province.data)
                    }
                    is com.nifcompany.pantaucovid19.core.data.Resource.Error -> {
                        binding.PbMain.visibility = View.GONE
                    }
                }
            }
        })



        binding.ContentMain.SvProvince.queryHint = "Cari Nama Provinsi"
        binding.ContentMain.SvProvince.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    homeViewModel.setSearchQuery(it)
                }
                return true
            }
        })

        homeViewModel.provinceListLiveData.observe(this, Observer {
            provinceAdapter.setData(it)
        })

        with(binding.ContentMain.RvMain) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = provinceAdapter
        }
    }

    private fun setBindingMain(data: Indonesia?) {

        binding.TvMainDateUpdate.text   = data?.date
        binding.TvMainPositif.text      = formatNumber(data?.positif)
        binding.TvMainRawat.text        = formatNumber(data?.dirawat)
        binding.TvMainSembuh.text       = formatNumber(data?.sembuh)
        binding.TvMainMeninggal.text    = formatNumber(data?.meninggal)

        binding.FabMain.setOnClickListener {
            val intent = Intent(this, HomeDetailActivity::class.java)
            intent.putExtra(EXTRA_DATA, data)
            startActivity(intent)
        }


    }

    private fun formatNumber(number : Int?): String {
        return String.format("%,d", number)
    }
}