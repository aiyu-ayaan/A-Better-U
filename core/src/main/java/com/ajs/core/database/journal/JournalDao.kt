package com.ajs.core.database.journal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournal(model: JournalModel)

    @Query("SELECT * FROM journal ORDER BY created DESC")
    fun getAllJournal(): Flow<List<JournalModel>>

    @Query("SELECT * FROM journal WHERE id = :id")
    suspend fun getJournalById(id: Int): JournalModel?

    @Query("DELETE FROM journal WHERE id = :id")
    suspend fun deleteJournalById(id: Int)

    @Query("DELETE FROM journal")
    suspend fun deleteAllJournal()
}