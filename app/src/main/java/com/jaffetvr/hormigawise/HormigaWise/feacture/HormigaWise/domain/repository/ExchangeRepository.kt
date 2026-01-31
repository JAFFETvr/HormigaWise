package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.repository

import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.entitie.ExchangeRate

interface ExchangeRepository {
    suspend fun getRates(base: String, symbols: String): List<ExchangeRate>
}