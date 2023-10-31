package com.verygoodconsulting.aiprompts.data.db

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.verygoodconsulting.aiprompts.domain.HomeUsecase
import com.verygoodconsulting.aiprompts.domain.HomeUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun appDatabase(@ApplicationContext app: Context): AppDatabase =
        Room.databaseBuilder(
            app, AppDatabase::class.java,
            "app_db"
        ).build()

    @Provides
    fun dao(appDatabase: AppDatabase): PromptDao =
        appDatabase.dao()

    @Provides
    fun dbApi(impl: DbApiImpl): DbApi = impl

    @Provides
    fun useCase(impl: HomeUsecaseImpl): HomeUsecase = impl
}