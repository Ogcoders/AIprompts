package com.verygoodconsulting.aiprompts.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.verygoodconsulting.aiprompts.model.db.PromptEntity

@Database(
    entities = [PromptEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): PromptDao
}