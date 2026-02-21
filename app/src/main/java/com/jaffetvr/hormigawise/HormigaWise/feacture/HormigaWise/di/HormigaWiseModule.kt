/*package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.di

import com.jaffetvr.hormigawise.HormigaWise.core.di.AppContainer
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases.GetExchangeRatesUseCase
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases.GetProductSuggestionUseCase
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.viewModels.ExchangeViewModelFactory

class HormigaWiseModule(
    private val appContainer: AppContainer
) {
    private fun provideGetExchangeRatesUseCase(): GetExchangeRatesUseCase {
        return GetExchangeRatesUseCase(appContainer.exchangeRepository)
    }

    private fun provideGetProductSuggestionUseCase(): GetProductSuggestionUseCase {
        return GetProductSuggestionUseCase(appContainer.fakeStoreApi)
    }

    fun provideExchangeViewModelFactory(): ExchangeViewModelFactory {
        return ExchangeViewModelFactory(
            getExchangeRatesUseCase = provideGetExchangeRatesUseCase(),
            getProductSuggestionUseCase = provideGetProductSuggestionUseCase(),
            gastoDao = appContainer.gastoDao
        )
    }
}*/