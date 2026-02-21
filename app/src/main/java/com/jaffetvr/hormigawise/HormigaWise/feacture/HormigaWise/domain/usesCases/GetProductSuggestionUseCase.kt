package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases

import android.util.Log
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.FakeStoreApi
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.entitie.ProductItem
import javax.inject.Inject

class GetProductSuggestionUseCase @Inject constructor(
    private val api: FakeStoreApi
) {
    suspend operator fun invoke(totalGastoUsd: Double): ProductItem? {
        return try {
            val products = api.getProducts()
            if (products.isEmpty()) return null

            val productosAlcanzables = products.filter { it.price <= totalGastoUsd }

            val productoSeleccionado = if (productosAlcanzables.isNotEmpty()) {
                productosAlcanzables.maxByOrNull { it.price }
            } else {
                products.minByOrNull { it.price }
            }

            productoSeleccionado?.let {
                ProductItem(
                    title = it.title,
                    priceUsd = it.price,
                    imageUrl = it.image
                )
            }
        } catch (e: Exception) {
            Log.e("HormigaWise", "Error en sugerencia: ${e.message}")
            null
        }
    }
}