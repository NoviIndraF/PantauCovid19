package com.nifcompany.pantaucovid19.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nifcompany.pantaucovid19.core.domain.usecase.CovidUseCase

class MapsViewModel(covidUseCase: CovidUseCase) : ViewModel() {
    val covid = covidUseCase.getDataProvince().asLiveData()
}

