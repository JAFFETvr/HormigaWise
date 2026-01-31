package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.screens

import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local.GastoEntity
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.entitie.ProductItem

data class ExchangeUiState(
    val isLoading: Boolean = false,
    val gastosHormiga: List<GastoEntity> = emptyList(),
    val tasaUsdActual: Double = 1.0,
    val totalGastosMxn: Double = 0.0,
    val productoSugerido: ProductItem? = null,
    val error: String? = null
)