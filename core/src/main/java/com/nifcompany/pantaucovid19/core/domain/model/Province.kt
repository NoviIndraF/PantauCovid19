package com.nifcompany.pantaucovid19.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Province (
    var id: Int,
    var name: String,
    var date: String,

    var kasus: Int,
    var dirawat: Int,
    var sembuh: Int,
    var meninggal: Int,

    var lakilaki: Int,
    var perempuan: Int,

    var kelompok1: Int,
    var kelompok2: Int,
    var kelompok3: Int,
    var kelompok4: Int,
    var kelompok5: Int,
    var kelompok6: Int,

    var pPositif: Int,
    var pSembuh: Int,
    var pMeninggal: Int,

    var lon: Double,
    var lat: Double,
    var isSave: Boolean
): Parcelable