package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gastos_table")
data class GastoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val descripcion: String,
    val montoMxn: Double,
    val fecha: String
)