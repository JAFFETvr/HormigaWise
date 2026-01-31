package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.repository

import com.jaffetvr.hormigawise.HormigaWise.core.network.FrankfurterApi
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.mapper.toDomain
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.entitie.ExchangeRate
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.repository.ExchangeRepository

class ExchangeRepositoryImpl(
    private val api: FrankfurterApi
) : ExchangeRepository {

    override suspend fun getRates(base: String, symbols: String): List<ExchangeRate> {
        val response = api.getLatestRates(base, symbols)
        return response.toDomain()
    }
}