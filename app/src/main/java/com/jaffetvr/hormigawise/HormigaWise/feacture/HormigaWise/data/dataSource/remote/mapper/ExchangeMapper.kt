package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.mapper

import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.model.ExchangeResponse
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.entitie.ExchangeRate

fun ExchangeResponse.toDomain(): List<ExchangeRate> {
    return this.rates.map { (symbol, value) ->
        ExchangeRate(
            currencyCode = symbol,
            rate = value
        )
    }
}