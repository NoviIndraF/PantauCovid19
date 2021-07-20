package com.nifcompany.pantaucovid19.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nifcompany.pantaucovid19.favorite.databinding.ActivitySaveProvinceBinding
import com.nifcompany.pantaucovid19.home.ProvinceAdapter
import com.nifcompany.pantaucovid19.provinceDetail.ProvinceDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SaveProvinceActivity : AppCompatActivity() {
    private val saveProvinceViewModel: SaveProvinceViewModel by viewModel()
    private lateinit var binding: ActivitySaveProvinceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveProvinceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(saveModule)
        val provinceAdapter = ProvinceAdapter()
        provinceAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, ProvinceDetailActivity::class.java)
            intent.putExtra(ProvinceDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        saveProvinceViewModel.saveProvince.observe(this, { dataTourism ->
            provinceAdapter.setData(dataTourism)
        })

        with(binding.RvSaveProvince) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = provinceAdapter

        }
    }
}