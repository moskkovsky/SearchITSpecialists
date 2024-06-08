package com.moskovsky.searchitspecialists.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_ROLE = "role"
        private const val ROLE_ADMIN = "ROLE_ADMIN"
        private const val ROLE_USER = "ROLE_USER"
    }

    var email: String?
        get() = sharedPreferences.getString(KEY_EMAIL, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_EMAIL, value).apply()
        }

    var role: String?
        get() = sharedPreferences.getString(KEY_ROLE, null)
        set(value) {
            sharedPreferences.edit().putString(KEY_ROLE, value).apply()
        }

    val isAdmin: Boolean
        get() = role == ROLE_ADMIN
}