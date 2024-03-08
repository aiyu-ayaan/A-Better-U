package com.ajs.core.module

import android.content.Context
import androidx.room.Room
import com.ajs.core.database.ABetterUDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext
        context: Context
    ): ABetterUDb =
        Room.databaseBuilder(
            context = context,
            klass = ABetterUDb::class.java,
            name = ABetterUDb.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesJournalDao(
        db: ABetterUDb
    ) = db.journalDao
}