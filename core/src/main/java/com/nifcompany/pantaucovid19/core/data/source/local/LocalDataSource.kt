package com.nifcompany.pantaucovid19.core.data.source.local

import com.nifcompany.pantaucovid19.core.data.source.local.entity.IndonesiaEntity
import com.nifcompany.pantaucovid19.core.data.source.local.entity.ProvinceEntity
import com.nifcompany.pantaucovid19.core.data.source.local.room.CovidDao

import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val covidDao: CovidDao) {


    fun getDataIndonesia(): Flow<IndonesiaEntity> = covidDao.getDataIndonesia()

    fun getDataProvince(): Flow<List<ProvinceEntity>> = covidDao.getDataProvince()

    fun getSearchProvince(search: String): Flow<List<ProvinceEntity>> = covidDao.getSearchProvince(search)

    fun getSaveDataProvince(): Flow<List<ProvinceEntity>> = covidDao.getSaveDataProvince()

    suspend fun insertDataIndonesia(indonesia: IndonesiaEntity) = covidDao.insertDataIndonesia(indonesia)

    suspend fun insertDataProvince(province: List<ProvinceEntity>) = covidDao.insertDataProvince(province)


    fun setSaveDataProvince(province: ProvinceEntity, newState: Boolean) {
        province.isSave = newState
        covidDao.updateSaveDataProvince(province)
    }
}