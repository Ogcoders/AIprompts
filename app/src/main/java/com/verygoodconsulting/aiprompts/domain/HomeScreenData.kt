package com.verygoodconsulting.aiprompts.domain

import com.verygoodconsulting.aiprompts.model.db.PromptEntity

sealed class HomeScreenData {
    data class Dummy(val msg: String) : HomeScreenData()
    data class Info(val items: List<PromptEntity>):HomeScreenData()
}
