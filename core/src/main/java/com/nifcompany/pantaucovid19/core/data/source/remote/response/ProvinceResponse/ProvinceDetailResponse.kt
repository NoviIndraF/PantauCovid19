package com.nifcompany.pantaucovid19.core.data.source.remote.response.ProvinceResponse

import com.google.gson.annotations.SerializedName

data class ProvinceDetailResponse(

	@field:SerializedName("provinsi")
	val provinsi: String,

	@field:SerializedName("penambahan")
	val penambahan: Penambahan,

	@field:SerializedName("meninggal")
	val meninggal: Int,

	@field:SerializedName("sembuh")
	val sembuh: Int,

	@field:SerializedName("dirawat")
	val dirawat: Int,

	@field:SerializedName("lokasi")
	val lokasi: Lokasi,

	@field:SerializedName("kelompok_umur")
	val kelompokUmur: KelompokUmur,

	@field:SerializedName("kasus")
	val kasus: Int,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: JenisKelamin,

	@field:SerializedName("last_date")
	val lastDate: String
)

data class KelompokUmur(

	@field:SerializedName("31-45")
	val jsonMember3145: Int,

	@field:SerializedName("≥ 60")
	val jsonMember60: Int,

	@field:SerializedName("6-18")
	val jsonMember618: Int,

	@field:SerializedName("0-5")
	val jsonMember05: Int,

	@field:SerializedName("46-59")
	val jsonMember4659: Int,

	@field:SerializedName("19-30")
	val jsonMember1930: Int
)

data class Lokasi(

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("lat")
	val lat: Double
)

data class Penambahan(

	@field:SerializedName("meninggal")
	val meninggal: Int,

	@field:SerializedName("positif")
	val positif: Int,

	@field:SerializedName("sembuh")
	val sembuh: Int
)

data class JenisKelamin(

	@field:SerializedName("laki-laki")
	val lakiLaki: Int,

	@field:SerializedName("perempuan")
	val perempuan: Int
)
