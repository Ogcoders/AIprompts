package com.verygoodconsulting.aiprompts.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PromptEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val instruction: String
) {
}