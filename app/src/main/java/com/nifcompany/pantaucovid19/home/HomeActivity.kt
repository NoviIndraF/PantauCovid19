package com.nifcompany.pantaucovid19.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.nifcompany.pantaucovid19.core.domain.model.Indonesia
import com.nifcompany.pantaucovid19.databinding.ActivityMainBinding
import com.nifcompany.pantaucovid19.databinding.LayoutMainPlaceholderBinding
import com.nifcompany.pantaucovid19.homeDetail.HomeDetailActivity
import com.nifcompany.pantaucovid19.homeDetail.HomeDetailActivity.Companion.EXTRA_DATA
import com.nifcompany.pantaucovid19.provinceDetail.ProvinceDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: LayoutMainPlaceholderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = LayoutMainPlaceholderBinding.inflate(layoutInflater)
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

        homeViewModel.dataIndonesia.observe(this, { indonesia ->
            if (indonesia != null) {
                when (indonesia) {
                    is com.nifcompany.pantaucovid19.core.data.Resource.Loading -> {
                        binding.ContentPlaceHolder.root.visibility = View.VISIBLE
                    }
                    is com.nifcompany.pantaucovid19.core.data.Resource.Success -> {
                        binding.ContentPlaceHolder.root.visibility = View.GONE
                        setBindingMain(indonesia.data)
                    }
                    is com.nifcompany.pantaucovid19.core.data.Resource.Error -> {
                        binding.ContentPlaceHolder.root.visibility = View.GONE
                        binding.ContentError.root.visibility = View.VISIBLE
                    }
                }
            }
        })

        homeViewModel.dataProvince.observe(this, { province ->
            if (province != null) {
                when (province) {
                    is com.nifcompany.pantaucovid19.core.data.Resource.Loading -> {
                        binding.ContentCardMain.visibility = View.INVISIBLE}
                    is com.nifcompany.pantaucovid19.core.data.Resource.Success -> {
                        binding.ContentCardMain.visibility = View.VISIBLE
                        provinceAdapter.setData(province.data)
                    }
                    is com.nifcompany.pantaucovid19.core.data.Resource.Error -> {
                        binding.ContentCardMain.visibility = View.INVISIBLE
                        binding.ContentError.root.visibility = View.VISIBLE
                    }
                }
            }
        })



        binding.SvProvince.queryHint = "Cari Nama Provinsi"
        binding.SvProvince.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

        homeViewModel.provinceListLiveData.observe(this, {
            provinceAdapter.setData(it)
        })

        with(binding.RvMain) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = provinceAdapter
        }
    }

    private fun setBindingMain(data: Indonesia?) {

        binding.TvMainDateUpdate.text   = data?.date
        binding.TvMainPositif.text      = formatNumber(data?.positif)
        binding.TvMainRawat.text        = formatNumber(data?.dirawat)
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