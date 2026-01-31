package com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote

import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.model.ProductDto
import retrofit2.http.GET

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}