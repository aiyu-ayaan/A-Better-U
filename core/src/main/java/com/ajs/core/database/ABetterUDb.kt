package com.ajs.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajs.core.database.journal.JournalDao
import com.ajs.core.database.journal.JournalModel


@Database(
    entities = [JournalModel::class], version = 1
)
abstract class ABetterUDb : RoomDatabase() {
    abstract val journalDao: JournalDao

    companion object {
        const val DB_NAME = "a_better_u_db"
    }
}