package com.nifcompany.pantaucovid19.core.utils

import android.util.Log
import com.nifcompany.pantaucovid19.core.data.source.local.entity.IndonesiaEntity
import com.nifcompany.pantaucovid19.core.domain.model.Province
import com.nifcompany.pantaucovid19.core.data.source.local.entity.ProvinceEntity
import com.nifcompany.pantaucovid19.core.data.source.remote.response.IndonesiaResponse.IndonesiaDetailResponse
import com.nifcompany.pantaucovid19.core.data.source.remote.response.ProvinceResponse.ProvinceDetailResponse
import com.nifcompany.pantaucovid19.core.domain.model.Indonesia
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToEntitiesProvince(input: List<ProvinceDetailResponse>): List<ProvinceEntity> {
        val provinceList = ArrayList<ProvinceEntity>()
        input.map {
            val province =
                ProvinceEntity(
                    id = null,
                    date = it.lastDate,
                    name = it.provinsi,
                    lakilaki = it.jenisKelamin.lakiLaki,
                    perempuan = it.jenisKelamin.perempuan,

                    kasus = it.kasus,
                    dirawat = it.dirawat,
                    sembuh = it.sembuh,
                    meninggal = it.meninggal,

                    pPositif = it.penambahan.positif,
                    pSembuh = it.penambahan.sembuh,
                    pMeninggal = it.penambahan.meninggal,

                    kelompok1 = it.kelompokUmur.jsonMember05,
                    kelompok2 = it.kelompokUmur.jsonMember618,
                    kelompok3 = it.kelompokUmur.jsonMember1930,
                    kelompok4 = it.kelompokUmur.jsonMember3145,
                    kelompok5 = it.kelompokUmur.jsonMember4659,
                    kelompok6 = it.kelompokUmur.jsonMember60,

                    lat = it.lokasi.lat,
                    lon = it.lokasi.lon,

                    isSave = false
                )
            provinceList.add(province)
        }
        return provinceList
    }

    fun mapEntitiesToDomainProvince(input: List<ProvinceEntity>): List<Province> =
        input.map{
                Province(
                    id = it.id!!,
                    date = it.date,
                    name = it.name,
                    lakilaki = it.lakilaki,
                    perempuan = it.perempuan,

                    kasus = it.kasus,
                    dirawat = it.dirawat,
                    sembuh = it.sembuh,
                    meninggal = it.meninggal,

                    pPositif = it.pPositif,
                    pSembuh = it.pSembuh,
                    pMeninggal = it.pMeninggal,

                    kelompok1 = it.kelompok1,
                    kelompok2 = it.kelompok2,
                    kelompok3 = it.kelompok3,
                    kelompok4 = it.kelompok4,
                    kelompok5 = it.kelompok5,
                    kelompok6 = it.kelompok6,

                    lat = it.lat,
                    lon = it.lon,

                    isSave = it.isSave
                )
        }

    fun mapDomainToEntityProvince(input: Province)=
        ProvinceEntity(
            id = input.id,
            date = input.date,
            name = input.name,
            lakilaki = input.lakilaki,
            perempuan = input.perempuan,

            kasus = input.kasus,
            dirawat = input.dirawat,
            sembuh = input.sembuh,
            meninggal = input.meninggal,

            pPositif = input.pPositif,
            pSembuh = input.pSembuh,
            pMeninggal = input.pMeninggal,

            kelompok1 = input.kelompok1,
            kelompok2 = input.kelompok2,
            kelompok3 = input.kelompok3,
            kelompok4 = input.kelompok4,
            kelompok5 = input.kelompok5,
            kelompok6 = input.kelompok6,

            lat = input.lat,
            lon = input.lon,

            isSave = input.isSave
        )

    fun mapEntityToDomainIndonesia(input: IndonesiaDetailResponse) : Flow<Indonesia> {
        return flowOf(
         Indonesia(
            id = null,
            date = input.penambahan.tanggal,

            positif = input.total.positif,
            dirawat = input.total.dirawat,
            sembuh = input.total.sembuh,
            meninggal = input.total.meninggal,

            pPositif = input.penambahan.positif,
            pDirawat = input.penambahan.dirawat,
            pSembuh = input.penambahan.sembuh,
            pMeninggal = input.penambahan.meninggal,

            odp = input.data.odp,
            pdp = input.data.pdp,
            total_spesimen = input.data.totalSpesimen,
            total_spesimen_negatif = input.data.totalSpesimenNegatif))
    }



    fun mapResponsesToEntitiesIndonesia(input: IndonesiaDetailResponse): IndonesiaEntity =
        IndonesiaEntity(
            id = null,
            date = input.penambahan.tanggal,

            positif = input.total.positif,
            dirawat = input.total.dirawat,
            sembuh = input.total.sembuh,
            meninggal = input.total.meninggal,

            pPositif = input.penambahan.positif,
            pDirawat = input.penambahan.dirawat,
            pSembuh = input.penambahan.sembuh,
            pMeninggal = input.penambahan.meninggal,

            odp = input.data.odp,
            pdp = input.data.pdp,
            total_spesimen = input.data.totalSpesimen,
            total_spesimen_negatif = input.data.totalSpesimenNegatif
        )
}