package com.nifcompany.pantaucovid19.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Indonesia(
    var id: Int?,
    var date: String,

    var positif: Int,
    var dirawat: Int,
    var sembuh: Int,
    var meninggal: Int,

    var pPositif: Int,
    var pDirawat: Int,
    var pSembuh: Int,
    var pMeninggal: Int,

    var odp: Int,
    var pdp: Int,
    var total_spesimen: Int,
    var total_spesimen_negatif: Int
): Parcelable