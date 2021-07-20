package com.nifcompany.pantaucovid19.favorite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val saveModule = module {
    viewModel { SaveProvinceViewModel(get()) }
}