package com.nifcompany.pantaucovid19.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.nifcompany.pantaucovid19.core.domain.usecase.CovidUseCase
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest

class HomeViewModel(private val covidUseCase: CovidUseCase) : ViewModel()  {
    val dataIndonesia   = covidUseCase.getDataIndonesia().asLiveData()
    val dataProvince    = covidUseCase.getDataProvince().asLiveData()

    private val searchChanel = ConflatedBroadcastChannel<String>()

    fun setSearchQuery(search: String) {
        searchChanel.offer(search)
    }

    val provinceListLiveData = searchChanel.asFlow()
        .flatMapLatest { search ->
            covidUseCase.getSearchProvince(search)
        }
        .catch { throwable ->
            FirebaseCrashlytics.getInstance().log("Error/HomeViewModel: "+ throwable.message.toString())
        }.asLiveData()
}
