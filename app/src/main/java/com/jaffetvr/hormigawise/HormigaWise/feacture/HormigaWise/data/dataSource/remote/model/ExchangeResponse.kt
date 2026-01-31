package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.model

data class ExchangeResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)