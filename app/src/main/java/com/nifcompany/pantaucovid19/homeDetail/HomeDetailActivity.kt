package com.nifcompany.pantaucovid19.homeDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nifcompany.pantaucovid19.core.domain.model.Indonesia
import com.nifcompany.pantaucovid19.databinding.ActivityMainDetailBinding
import com.nifcompany.pantaucovid19.map.MapActivity

class HomeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.FabMainMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        val detailIndonesia = intent.getParcelableExtra<Indonesia>(EXTRA_DATA)
        showDetailIndonesia(detailIndonesia)
    }

    private fun showDetailIndonesia(detailIndonesia: Indonesia?) {
        detailIndonesia?.let {

            binding.TvHomeDetailDateUpdate.text = detailIndonesia.date

            binding.TvHomeDetailPositif.text = formatNumber(detailIndonesia.positif)
            binding.TvHomeDetailSembuh.text = formatNumber(detailIndonesia.sembuh)
            binding.TvHomeDetailRawat.text = formatNumber(detailIndonesia.dirawat)
            binding.TvHomeDetailMeninggal.text = formatNumber(detailIndonesia.meninggal)

            binding.ContentHomeDetail.TvMainDetailPositif.text = formatNumber(detailIndonesia.pPositif)
            binding.ContentHomeDetail.TvMainDetailSembuh.text = formatNumber(detailIndonesia.pSembuh)
            binding.ContentHomeDetail.TvMainDetailDirawat.text = formatNumber(detailIndonesia.pDirawat)
            binding.ContentHomeDetail.TvMainDetailMeninggal.text = formatNumber(detailIndonesia.pMeninggal)

            binding.ContentHomeDetail.TvMainDetailOdp.text = formatNumber(detailIndonesia.odp)
            binding.ContentHomeDetail.TvMainDetailPdp.text = formatNumber(detailIndonesia.pdp)
            binding.ContentHomeDetail.TvMainDetailTotSpesimen.text = formatNumber(detailIndonesia.total_spesimen)
            binding.ContentHomeDetail.TvMainDetailTotSpesimenNegatif.text = formatNumber(detailIndonesia.total_spesimen_negatif)
        }
    }

    private fun formatNumber(number : Int?): String {
        return String.format("%,d", number)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}