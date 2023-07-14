package com.example.suitmediaproject

import android.content.Context

internal class Preferences(context: Context) {
    companion object{
        private const val PREFS_NAME = "pref"
        private const val USER = "user"
        private const val JOBS = "jobs"
        private const val SCORE = "score"
        private const val FILE = "file"
    }
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun saveUser(data: UserModel){
        val editor = preferences.edit()
        editor.putString(USER, data.Name)
        editor.apply()
    }

    fun getUser(): UserModel {
        val token = preferences.getString(USER, null)
        return UserModel(token)
    }
}