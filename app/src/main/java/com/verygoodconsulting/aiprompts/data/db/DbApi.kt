package com.verygoodconsulting.aiprompts.data.db

import com.verygoodconsulting.aiprompts.model.db.PromptEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface DbApi {
    fun fetchPrompts(): Flow<List<PromptEntity>>

    suspend fun save(items: List<PromptEntity>)

    suspend fun fetchAllPromptsRaw(): List<PromptEntity>
}


@Singleton
class DbApiImpl @Inject constructor(
    private val mDao: PromptDao
) : DbApi {
    override fun fetchPrompts(): Flow<List<PromptEntity>> {
        return mDao.fetchPrompts()
    }

    override suspend fun save(items: List<PromptEntity>) {
        mDao.savePrompts(items)
    }

    override suspend fun fetchAllPromptsRaw(): List<PromptEntity> {
        return mDao.fetchPromptsRaw()
    }
}