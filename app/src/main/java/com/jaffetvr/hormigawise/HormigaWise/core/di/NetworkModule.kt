package com.jaffetvr.hormigawise.HormigaWise.core.di

import com.jaffetvr.hormigawise.HormigaWise.core.network.FrankfurterApi
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.remote.FakeStoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FrankfurterRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FakeStoreRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @FrankfurterRetrofit
    fun provideFrankfurterRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.frankfurter.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    @FakeStoreRetrofit
    fun provideFakeStoreRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideFrankfurterApi(@FrankfurterRetrofit retrofit: Retrofit): FrankfurterApi =
        retrofit.create(FrankfurterApi::class.java)

    @Provides
    @Singleton
    fun provideFakeStoreApi(@FakeStoreRetrofit retrofit: Retrofit): FakeStoreApi =
        retrofit.create(FakeStoreApi::class.java)
}