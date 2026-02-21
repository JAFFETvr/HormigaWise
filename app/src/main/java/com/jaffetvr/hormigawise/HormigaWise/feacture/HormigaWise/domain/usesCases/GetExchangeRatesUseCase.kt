package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases

import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.entitie.ExchangeRate
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.repository.ExchangeRepository
import javax.inject.Inject

class GetExchangeRatesUseCase @Inject constructor(
    private val repository: ExchangeRepository
) {
    suspend operator fun invoke(base: String, symbols: String): Result<List<ExchangeRate>> {
        return try {
            val rates = repository.getRates(base, symbols)
            if (rates.isEmpty()) {
                Result.failure(Exception("No se encontraron tasas de cambio disponibles"))
            } else {
                Result.success(rates)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}