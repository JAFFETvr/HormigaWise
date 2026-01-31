package com.jaffetvr.hormigawise.HormigaWise.core.network

import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FrankfurterApi {
    @GET("v1/latest")
    suspend fun getLatestRates(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): ExchangeResponse
}