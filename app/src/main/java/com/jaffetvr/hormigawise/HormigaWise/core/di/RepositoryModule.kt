package com.jaffetvr.hormigawise.HormigaWise.core.di

import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.repository.ExchangeRepositoryImpl
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.domain.repository.ExchangeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindExchangeRepository(impl: ExchangeRepositoryImpl): ExchangeRepository
}