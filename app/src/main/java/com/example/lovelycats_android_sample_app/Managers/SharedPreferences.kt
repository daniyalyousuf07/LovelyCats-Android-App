package com.example.lovelycats_android_sample_app.Managers

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app-shared-preference",
            Context.MODE_PRIVATE)

    fun save(key: String,
                 value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun get(key: String,
                defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue) ?: defaultValue
    }
}