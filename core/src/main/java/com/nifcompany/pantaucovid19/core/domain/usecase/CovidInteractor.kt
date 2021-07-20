package com.nifcompany.pantaucovid19.core.domain.usecase

import com.nifcompany.pantaucovid19.core.domain.model.Province
import com.nifcompany.pantaucovid19.core.domain.repository.ICovidRepository


class CovidInteractor(private val covidRepository: ICovidRepository): CovidUseCase {
    override fun getDataIndonesia() = covidRepository.getDataIndonesia()

    override fun getDataProvince() = covidRepository.getDataProvince()

    override fun getSearchProvince(search: String)=covidRepository.getSearchProvince(search)

    override fun getSaveDataProvince() = covidRepository.getSaveDataProvince()

    override fun setSaveDataProvince(province: Province, state: Boolean) = covidRepository.setSaveDataProvince(province, state)
}