package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GastoDao {
    @Query("SELECT * FROM gastos_table ORDER BY id DESC")
    suspend fun getAllGastos(): List<GastoEntity>

    @Insert
    suspend fun insertGasto(gasto: GastoEntity)
}