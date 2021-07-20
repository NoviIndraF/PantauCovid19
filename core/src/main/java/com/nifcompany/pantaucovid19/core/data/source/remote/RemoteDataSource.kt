package com.nifcompany.pantaucovid19.core.data.source.remote

import android.util.Log
import com.nifcompany.pantaucovid19.core.data.source.remote.network.ApiResponse
import com.nifcompany.pantaucovid19.core.data.source.remote.network.ApiService
import com.nifcompany.pantaucovid19.core.data.source.remote.response.IndonesiaResponse.IndonesiaDetailResponse
import com.nifcompany.pantaucovid19.core.data.source.remote.response.ProvinceResponse.ProvinceDetailResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor (
    private val apiService: ApiService
    ) {

    suspend fun getDataIndonesia(): Flow<ApiResponse<IndonesiaDetailResponse>> {
        return flow {
            try {
                val response = apiService.getIndonesiaData()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDataProvince(): Flow<ApiResponse<List<ProvinceDetailResponse>>> {
        return flow {
            try {
                val response = apiService.getProvinceData()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}