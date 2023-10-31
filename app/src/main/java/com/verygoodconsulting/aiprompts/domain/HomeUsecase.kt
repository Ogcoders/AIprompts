package com.verygoodconsulting.aiprompts.domain

import android.app.Application
import com.verygoodconsulting.aiprompts.data.db.DbApi
import com.verygoodconsulting.aiprompts.model.db.PromptEntity
import com.verygoodconsulting.aiprompts.utils.readText
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

interface HomeUsecase {
    suspend fun loadInfo()

    fun data(): Flow<HomeScreenData>
}

class HomeUsecaseImpl @Inject constructor(
    private val mDbApi: DbApi,
    private val mApp: Application
) : HomeUsecase {
    override suspend fun loadInfo() {
        val dataExists = mDbApi.fetchAllPromptsRaw().isNotEmpty()
        if (dataExists.not()) {
            val rawString = mApp.assets.readText("raw.json")
            val jsonArray = JSONArray(rawString)
            val items = mutableListOf<PromptEntity>()
            for (index in 0..jsonArray.length() - 1) {
                val obj: JSONObject = jsonArray.getJSONObject(index)
                items.add(
                    PromptEntity(
                        id = obj.optInt("id"),
                        name = obj.optString("name"),
                        description = obj.optString("prompt"),
                        instruction = obj.getString("instruction")
                    )
                )
            }
            mDbApi.save(items)
        }
    }

    override fun data(): Flow<HomeScreenData> {
        return mDbApi.fetchPrompts().map {
            if (it.isEmpty()) {
                HomeScreenData.Dummy("No data here")
            } else {
                HomeScreenData.Info(it)
            }
        }
    }

}