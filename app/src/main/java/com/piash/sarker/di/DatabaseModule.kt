package com.piash.sarker.di

import android.content.Context
import com.piash.sarker.database.AppDatabase
import com.piash.sarker.database.DataStorageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideDataStorageDao(appDatabase: AppDatabase): DataStorageDao {
        return appDatabase.dataStorageDao()
    }


}