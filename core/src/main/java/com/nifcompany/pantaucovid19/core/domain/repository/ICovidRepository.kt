package com.nifcompany.pantaucovid19.core.domain.repository

import com.nifcompany.pantaucovid19.core.data.Resource
import com.nifcompany.pantaucovid19.core.domain.model.Indonesia
import com.nifcompany.pantaucovid19.core.domain.model.Province
import kotlinx.coroutines.flow.Flow

interface ICovidRepository {
    fun getDataIndonesia(): Flow<Resource<Indonesia>>

    fun getDataProvince(): Flow<Resource<List<Province>>>

    fun getSearchProvince(search: String): Flow<List<Province>>

    fun getSaveDataProvince(): Flow<List<Province>>

    fun setSaveDataProvince(province: Province, state: Boolean)
}