package com.nifcompany.pantaucovid19.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nifcompany.pantaucovid19.core.domain.usecase.CovidUseCase

class SaveProvinceViewModel(covidUseCase: CovidUseCase) : ViewModel() {
    val saveProvince = covidUseCase.getSaveDataProvince().asLiveData()
}