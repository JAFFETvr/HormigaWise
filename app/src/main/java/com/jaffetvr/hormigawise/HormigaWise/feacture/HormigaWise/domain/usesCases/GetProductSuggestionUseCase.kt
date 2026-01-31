package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases

import android.util.Log
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.FakeStoreApi
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.entitie.ProductItem

class GetProductSuggestionUseCase(private val api: FakeStoreApi) {
    suspend operator fun invoke(totalGastoUsd: Double): ProductItem? {
        Log.e("DIAGNOSTICO", "--------------------------------------------------")
        Log.e("DIAGNOSTICO", "1. INICIANDO BÚSQUEDA. Presupuesto Usuario: $$totalGastoUsd USD")

        return try {
            Log.d("DIAGNOSTICO", "2. Llamando a la API (api.getProducts)...")

            val products = api.getProducts()

            Log.d("DIAGNOSTICO", "3. ¡La API respondió! Se recibieron ${products.size} productos.")

            if (products.isEmpty()) {
                Log.e("DIAGNOSTICO", "4. ERROR: La lista llegó vacía. Revisa si la API cambió.")
                return null
            }

            val masBarato = products.minOf { it.price }
            Log.d("DIAGNOSTICO", "   - Producto más barato de la tienda: $$masBarato")
            Log.d("DIAGNOSTICO", "   - Producto más caro de la tienda: $${products.maxOf { it.price }}")

            val productosAlcanzables = products.filter { it.price <= totalGastoUsd }

            val productoSeleccionado = if (productosAlcanzables.isNotEmpty()) {
                Log.d("DIAGNOSTICO", "5. ÉXITO: Te alcanza para ${productosAlcanzables.size} productos.")
                productosAlcanzables.maxByOrNull { it.price }
            } else {
                Log.e("DIAGNOSTICO", "5. AVISO: No te alcanza para nada. (Tu dinero < $masBarato).")
                Log.e("DIAGNOSTICO", "   -> Activando MODO MOTIVACIÓN (Mostrando el más barato).")
                products.minByOrNull { it.price }
            }

            productoSeleccionado?.let {
                Log.e("DIAGNOSTICO", "6. RETORNANDO PRODUCTO: ${it.title}")
                Log.e("DIAGNOSTICO", "--------------------------------------------------")
                ProductItem(
                    title = it.title,
                    priceUsd = it.price,
                    imageUrl = it.image
                )
            }

        } catch (e: Exception) {
            Log.e("DIAGNOSTICO", "❌ ERROR FATAL / CRASH:")
            Log.e("DIAGNOSTICO", "   Mensaje: ${e.message}")
            Log.e("DIAGNOSTICO", "   Causa: ${e.cause}")

            if (e is retrofit2.HttpException) {
                Log.e("DIAGNOSTICO", "   Código HTTP: ${e.code()}")
                Log.e("DIAGNOSTICO", "   URL Intentada: Confirma en AppContainer si usaste FakeStoreApi o FrankfurterApi")
            }

            e.printStackTrace()
            null
        }
    }
}