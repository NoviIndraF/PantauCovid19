package com.nifcompany.pantaucovid19.core.data

import com.nifcompany.pantaucovid19.core.data.source.local.LocalDataSource
import com.nifcompany.pantaucovid19.core.data.source.remote.RemoteDataSource
import com.nifcompany.pantaucovid19.core.data.source.remote.network.ApiResponse
import com.nifcompany.pantaucovid19.core.data.source.remote.response.IndonesiaResponse.IndonesiaDetailResponse
import com.nifcompany.pantaucovid19.core.data.source.remote.response.ProvinceResponse.ProvinceDetailResponse
import com.nifcompany.pantaucovid19.core.domain.model.Indonesia
import com.nifcompany.pantaucovid19.core.domain.model.Province
import com.nifcompany.pantaucovid19.core.domain.repository.ICovidRepository
import com.nifcompany.pantaucovid19.core.utils.AppExecutors
import com.nifcompany.pantaucovid19.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CovidRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICovidRepository {

    override fun getDataIndonesia(): Flow<Resource<Indonesia>> =
        object : NetworkBoundResource<Indonesia, IndonesiaDetailResponse>() {
            override fun loadFromDB(data: IndonesiaDetailResponse): Flow<Indonesia> {

                return DataMapper.mapEntityToDomainIndonesia(data)

            }

            override fun shouldFetch(data: Indonesia?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<IndonesiaDetailResponse>> =
                remoteDataSource.getDataIndonesia()

            override suspend fun saveCallResult(data: IndonesiaDetailResponse) {
                val indonesiaData = DataMapper.mapResponsesToEntitiesIndonesia(data)
                localDataSource.insertDataIndonesia(indonesiaData)
            }
        }.asFlow()


    override fun getDataProvince(): Flow<Resource<List<Province>>> =
        object : NetworkBoundResource<List<Province>, List<ProvinceDetailResponse>>() {
            override fun loadFromDB(data: List<ProvinceDetailResponse> ): Flow<List<Province>> {
                return localDataSource.getDataProvince().map { DataMapper.mapEntitiesToDomainProvince(it) }
            }

            override fun shouldFetch(data: List<Province>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ProvinceDetailResponse>>> =
                remoteDataSource.getDataProvince()

            override suspend fun saveCallResult(data: List<ProvinceDetailResponse>) {
                val provinceList = DataMapper.mapResponsesToEntitiesProvince(data)
                localDataSource.insertDataProvince(provinceList)
            }
        }.asFlow()

    override fun getSearchProvince(search: String): Flow<List<Province>> {

        return localDataSource.getSearchProvince(search).map { DataMapper.mapEntitiesToDomainProvince(it) }
    }

    override fun getSaveDataProvince(): Flow<List<Province>> {

        return localDataSource.getSaveDataProvince().map { DataMapper.mapEntitiesToDomainProvince(it) }
    }


    override fun setSaveDataProvince(province: Province, state: Boolean) {
        val provinceEntity   = DataMapper.mapDomainToEntityProvince(province)
        appExecutors.diskIO().execute { localDataSource.setSaveDataProvince(provinceEntity, state) }
    }
}