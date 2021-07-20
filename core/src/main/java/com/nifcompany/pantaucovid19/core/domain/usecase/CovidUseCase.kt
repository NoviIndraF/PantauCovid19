package com.nifcompany.pantaucovid19.core.domain.usecase

import com.nifcompany.pantaucovid19.core.domain.model.Province
import com.nifcompany.pantaucovid19.core.data.Resource
import com.nifcompany.pantaucovid19.core.data.source.local.entity.IndonesiaEntity
import com.nifcompany.pantaucovid19.core.domain.model.Indonesia

import kotlinx.coroutines.flow.Flow

interface CovidUseCase {

    fun getDataIndonesia(): Flow<Resource<Indonesia>>

    fun getDataProvince(): Flow<Resource<List<Province>>>

    fun getSearchProvince(search: String): Flow<List<Province>>

    fun getSaveDataProvince(): Flow<List<Province>>

    fun setSaveDataProvince(province: Province, state: Boolean)
}