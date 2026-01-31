package com.jaffetvr.hormigawise.HormigaWise.core.di

import android.content.Context
import androidx.room.Room
import com.jaffetvr.hormigawise.HormigaWise.core.network.FrankfurterApi
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local.AppDatabase
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.repository.ExchangeRepositoryImpl
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.repository.ExchangeRepository
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.FakeStoreApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    private val retrofitFrankfurter: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.frankfurter.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val frankfurterApi: FrankfurterApi by lazy {
        retrofitFrankfurter.create(FrankfurterApi::class.java)
    }

    private val retrofitStore: Retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val fakeStoreApi: FakeStoreApi by lazy {
        retrofitStore.create(FakeStoreApi::class.java)
    }

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "hormigawise_db"
        ).build()
    }

    val gastoDao by lazy { database.gastoDao() }

    val exchangeRepository: ExchangeRepository by lazy {
        ExchangeRepositoryImpl(frankfurterApi)
    }
}