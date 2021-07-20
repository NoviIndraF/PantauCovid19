package com.nifcompany.pantaucovid19.di

import com.nifcompany.pantaucovid19.core.domain.usecase.CovidInteractor
import com.nifcompany.pantaucovid19.core.domain.usecase.CovidUseCase
import com.nifcompany.pantaucovid19.home.HomeViewModel
import com.nifcompany.pantaucovid19.provinceDetail.ProvinceDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CovidUseCase> { CovidInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ProvinceDetailViewModel(get()) }
}