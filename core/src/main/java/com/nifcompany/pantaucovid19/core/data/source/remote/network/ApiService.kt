package com.nifcompany.pantaucovid19.core.data.source.remote.network

import com.nifcompany.pantaucovid19.core.data.source.remote.response.IndonesiaResponse.IndonesiaDetailResponse
import com.nifcompany.pantaucovid19.core.data.source.remote.response.ProvinceResponse.ProvinceDetailResponse

import retrofit2.http.GET

interface ApiService {
    @GET("indonesia/more")
    suspend fun getIndonesiaData(): IndonesiaDetailResponse

    @GET("indonesia/provinsi/more")
    suspend fun getProvinceData(): List<ProvinceDetailResponse>
}