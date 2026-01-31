package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local.GastoDao
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local.GastoEntity
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases.GetExchangeRatesUseCase
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.usesCases.GetProductSuggestionUseCase
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.screens.ExchangeUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ExchangeViewModel(
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
    private val getProductSuggestionUseCase: GetProductSuggestionUseCase, // Usamos tu caso de uso real
    private val gastoDao: GastoDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExchangeUiState(tasaUsdActual = 0.05))
    val uiState = _uiState.asStateFlow()

    val gastosAgrupados = _uiState
        .map { state -> state.gastosHormiga.groupBy { it.fecha } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    init {
        loadRates("MXN", "USD")
        actualizarLista()
    }

    private fun loadRates(base: String, symbols: String) {
        viewModelScope.launch {
            try {
                val result = getExchangeRatesUseCase(base, symbols)
                result.onSuccess { rates ->
                    val tasaEncontrada = rates.find { it.currencyCode == "USD" }?.rate
                    if (tasaEncontrada != null) {
                        _uiState.update { it.copy(tasaUsdActual = tasaEncontrada) }
                        actualizarLista()
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    fun registrarGasto(desc: String, valor: Double) {
        viewModelScope.launch {
            val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            gastoDao.insertGasto(GastoEntity(descripcion = desc, montoMxn = valor, fecha = fechaActual))
            actualizarLista()
        }
    }

    fun actualizarLista() {
        viewModelScope.launch {
            val listaDeRoom = gastoDao.getAllGastos()
            val totalMxn = listaDeRoom.sumOf { it.montoMxn }


            _uiState.update { it.copy(
                gastosHormiga = listaDeRoom,
                totalGastosMxn = totalMxn
            ) }

            try {
                val tasa = _uiState.value.tasaUsdActual
                val totalUsd = totalMxn * tasa

                if (totalUsd > 0.5) {
                    val sugerencia = getProductSuggestionUseCase(totalUsd) // Tu UseCase real

                    if (sugerencia != null) {
                        _uiState.update { it.copy(productoSugerido = sugerencia) }
                    }
                }
            } catch (e: Exception) {
                println("Error obteniendo sugerencia: ${e.message}")
            }
        }
    }
}