package com.verygoodconsulting.aiprompts.utils

import android.content.res.AssetManager

fun AssetManager.readText(name: String): String =
    open(name).bufferedReader().use { it.readText() }
