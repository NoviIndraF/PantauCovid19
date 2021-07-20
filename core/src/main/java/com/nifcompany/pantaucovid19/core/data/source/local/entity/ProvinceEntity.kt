package com.nifcompany.pantaucovid19.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "dataProvinsi")
data class ProvinceEntity (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "kasus")
    var kasus: Int,

    @ColumnInfo(name = "dirawat")
    var dirawat: Int,

    @ColumnInfo(name = "sembuh")
    var sembuh: Int,

    @ColumnInfo(name = "meninggal")
    var meninggal: Int,

    @ColumnInfo(name = "lakilaki")
    var lakilaki: Int,

    @ColumnInfo(name="perempuan")
    var perempuan: Int,

    @ColumnInfo(name = "kelompok1")
    var kelompok1: Int,

    @ColumnInfo(name = "kelompok2")
    var kelompok2: Int,

    @ColumnInfo(name = "kelompok3")
    var kelompok3: Int,

    @ColumnInfo(name = "kelompok4")
    var kelompok4: Int,

    @ColumnInfo(name = "kelompok5")
    var kelompok5: Int,

    @ColumnInfo(name = "kelompok6")
    var kelompok6: Int,

    @ColumnInfo(name = "pPositif")
    var pPositif: Int,

    @ColumnInfo(name = "pSembuh")
    var pSembuh: Int,

    @ColumnInfo(name = "pMeninggal")
    var pMeninggal: Int,

    @ColumnInfo(name= "lon")
    var lon: Double,

    @ColumnInfo(name = "lat")
    var lat: Double,

    @ColumnInfo(name = "isSave")
    var isSave: Boolean
    ): Parcelable