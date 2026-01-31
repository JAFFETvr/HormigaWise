package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local.GastoEntity
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.viewModels.ExchangeViewModel
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.viewModels.ExchangeViewModelFactory
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.components.HormigaInputForm
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.components.HormigaGastoItem
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.presentation.components.ProductDialog
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHormigaScreen(factory: ExchangeViewModelFactory) {
    val viewModel: ExchangeViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val gastosPorDia by viewModel.gastosAgrupados.collectAsStateWithLifecycle()

    var mostrarProductoDialog by remember { mutableStateOf(false) }
    val fechaHoy = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

    Scaffold(
        containerColor = Color(0xFFFAFAFA),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("HormigaWise", fontWeight = FontWeight.SemiBold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            HormigaInputForm(
                onGastoRegistrado = { descripcion, monto ->
                    viewModel.registrarGasto(descripcion, monto)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            uiState.productoSugerido?.let {
                Button(
                    onClick = { mostrarProductoDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Icon(Icons.Default.ShoppingBag, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("👀 Mira lo que pudiste comprar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                gastosPorDia.forEach { (fecha: String, lista: List<GastoEntity>) ->
                    item(key = fecha) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = if (fecha == fechaHoy) "Hoy" else fecha, style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                            Text(text = "Total: $${"%.2f".format(lista.sumOf { it.montoMxn })}", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                        }
                    }
                    items(lista, key = { it.id }) { gasto ->
                        HormigaGastoItem(gasto = gasto, tasaUsd = uiState.tasaUsdActual)
                    }
                }
            }

            Surface(
                color = Color.White,
                shadowElevation = 10.dp,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total Acumulado", color = Color.Gray)
                    Text(
                        text = "$${"%.2f".format(uiState.totalGastosMxn)}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    if (mostrarProductoDialog && uiState.productoSugerido != null) {
        ProductDialog(
            producto = uiState.productoSugerido!!,
            onDismiss = { mostrarProductoDialog = false }
        )
    }
}