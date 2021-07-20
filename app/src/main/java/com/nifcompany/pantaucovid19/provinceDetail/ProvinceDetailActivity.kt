package com.nifcompany.pantaucovid19.provinceDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nifcompany.pantaucovid19.databinding.ActivityProvinceDetailBinding
import com.nifcompany.pantaucovid19.core.domain.model.Province
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProvinceDetailActivity : AppCompatActivity() {
    private val provinceDetailViewModel: ProvinceDetailViewModel by viewModel()
    private lateinit var binding: ActivityProvinceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProvinceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailProvince = intent.getParcelableExtra<Province>(EXTRA_DATA)
        showDetailProvince(detailProvince)
    }

    private fun showDetailProvince(detailProvince: Province?) {
        detailProvince?.let {

            binding.TvProvinceDetailName.text = detailProvince.name
            binding.TvProvinceDetailDateUpdate.text = detailProvince.date

            binding.TvProvinceDetailKasus.text = formatNumber(detailProvince.kasus)
            binding.TvProvinceDetailSembuh1.text = formatNumber(detailProvince.sembuh)
            binding.TvProvinceDetailRawat.text = formatNumber(detailProvince.dirawat)
            binding.TvProvinceDetailMeninggal.text = formatNumber(detailProvince.meninggal)

            binding.ContentProvince.TvProvinceDetailJkLaki.text = formatNumber(detailProvince.lakilaki)
            binding.ContentProvince.TvProvinceDetailJkPerempuan.text = formatNumber(detailProvince.perempuan)

            binding.ContentProvince.TvProvinceDetailPositif.text = formatNumber(detailProvince.pPositif)
            binding.ContentProvince.TvProvinceDetailSembuh.text = formatNumber(detailProvince.pSembuh)
            binding.ContentProvince.TvProvinceDetailMeninggal.text = formatNumber(detailProvince.pMeninggal)

            binding.ContentProvince.TvProvinceDetailKelompok1.text = formatNumber(detailProvince.kelompok1)
            binding.ContentProvince.TvProvinceDetailKelompok2.text = formatNumber(detailProvince.kelompok2)
            binding.ContentProvince.TvProvinceDetailKelompok3.text = formatNumber(detailProvince.kelompok3)
            binding.ContentProvince.TvProvinceDetailKelompok4.text = formatNumber(detailProvince.kelompok4)
            binding.ContentProvince.TvProvinceDetailKelompok5.text = formatNumber(detailProvince.kelompok5)
            binding.ContentProvince.TvProvinceDetailKelompok6.text = formatNumber(detailProvince.kelompok6)

            var statusSave = detailProvince.isSave
            setStatusSave(statusSave)


            binding.FabProvinceDetailSave.setOnClickListener {
                statusSave = !statusSave
                provinceDetailViewModel.setSaveProvince(detailProvince, statusSave)
                setStatusSave(statusSave)
            }
        }
    }

    private fun setStatusSave(statusSave: Boolean) {
        if (statusSave) {
            binding.FabProvinceDetailSave.text = "Disimpan"
        } else {
            binding.FabProvinceDetailSave.text = "Simpan"
        }
    }

    private fun formatNumber(number : Int?): String {
        return String.format("%,d", number)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}