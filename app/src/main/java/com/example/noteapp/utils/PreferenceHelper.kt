package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {

    private lateinit var sharedPreferences: SharedPreferences

    fun unit(context: Context) {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var isOnBoardingShowed: Boolean
        get() = sharedPreferences.getBoolean("onboard", false)
        set(value) = sharedPreferences.edit().putBoolean("onboard", value).apply()

    var isRecyclerViewGrid: Boolean
        get() = sharedPreferences.getBoolean("recyclerview", false)
        set(value) = sharedPreferences.edit().putBoolean("recyclerview", value).apply()

    var isSignInShowed: Boolean
        get() = sharedPreferences.getBoolean("sign", false)
        set(value) = sharedPreferences.edit().putBoolean("sign", value).apply()
}