package com.nifcompany.pantaucovid19.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "dataIndonesia")
data class IndonesiaEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name= "date")
    var date: String,

    @ColumnInfo(name = "positif")
    var positif: Int,

    @ColumnInfo(name = "dirawat")
    var dirawat: Int,

    @ColumnInfo(name = "sembuh")
    var sembuh: Int,

    @ColumnInfo(name = "meninggal")
    var meninggal: Int,

    @ColumnInfo(name = "pPositif")
    var pPositif: Int,

    @ColumnInfo(name = "pDirawat")
    var pDirawat: Int,

    @ColumnInfo(name = "pSembuh")
    var pSembuh: Int,

    @ColumnInfo(name = "pMeninggal")
    var pMeninggal: Int,

    @ColumnInfo(name = "odp")
    var odp: Int,

    @ColumnInfo(name = "pdp")
    var pdp: Int,

    @ColumnInfo(name = "totalSpesimen")
    var total_spesimen: Int,

    @ColumnInfo(name = "totalSpesimenNegatif")
    var total_spesimen_negatif: Int
): Parcelable