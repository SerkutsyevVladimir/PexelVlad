package com.example.pexelsvlad.di

import android.content.Context
import androidx.room.Room
import com.example.pexelsvlad.data.database.AppDatabase
import com.example.pexelsvlad.data.database.dao.DBPhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DB_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDBPhotoDao(appDatabase: AppDatabase): DBPhotoDao {
        return appDatabase.getDBPhotoDao()
    }

    companion object {
        private const val DB_NAME = "pexelsvlad_app_database.db"
    }
}