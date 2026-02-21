package com.jaffetvr.hormigawise.HormigaWise.core.di

import android.content.Context
import androidx.room.Room
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local.AppDatabase
import com.jaffetvr.hormigawise.HormigaWise.feacture.HormigaWise.data.dataSource.local.GastoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "hormigawise_db").build()

    @Provides
    fun provideGastoDao(db: AppDatabase): GastoDao = db.gastoDao()
}