package com.verygoodconsulting.aiprompts.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.verygoodconsulting.aiprompts.model.db.PromptEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PromptDao {

    @Query("select * from PromptEntity")
    fun fetchPrompts(): Flow<List<PromptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePrompts(items: List<PromptEntity>)

    @Query("select * from PromptEntity")
    suspend fun fetchPromptsRaw(): List<PromptEntity>
}