package com.nifcompany.pantaucovid19.provinceDetail

import androidx.lifecycle.ViewModel
import com.nifcompany.pantaucovid19.core.domain.model.Province
import com.nifcompany.pantaucovid19.core.domain.usecase.CovidUseCase

class ProvinceDetailViewModel(private val covidUseCase: CovidUseCase) : ViewModel() {
    fun setSaveProvince(province: Province, newStatus:Boolean) = covidUseCase.setSaveDataProvince(province, newStatus)
}