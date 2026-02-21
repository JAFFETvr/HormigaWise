/*package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local.GastoDao
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases.GetExchangeRatesUseCase
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases.GetProductSuggestionUseCase

class ExchangeViewModelFactory(
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
    private val getProductSuggestionUseCase: GetProductSuggestionUseCase, // Añadido
    private val gastoDao: GastoDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExchangeViewModel(
                getExchangeRatesUseCase,
                getProductSuggestionUseCase, // Pasado al ViewModel
                gastoDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/