package com.fquintal.test.remote

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.fquintal.test.remote.responses.DataUserResponse
import com.fquintal.test.remote.responses.UserLoggedIn
import com.google.gson.Gson

class DataStorage constructor(application: Application, private val gson: Gson) {

    private var preferences: SharedPreferences? = null

    init {
        preferences = application.getSharedPreferences(
            "remote_storage",
            Context.MODE_PRIVATE
        )
    }

    fun saveLogin(userLoggedIn: UserLoggedIn) {
        preferences!!.edit().putString("login_response", gson.toJson(userLoggedIn)).apply()
    }

    fun getLogin(): UserLoggedIn? {
        val str = preferences?.getString("login_response", "")
        return if (str.isNullOrEmpty())
            null
        else
            gson.fromJson(str, UserLoggedIn::class.java)
    }

    fun saveDataUser(dataUserResponse: DataUserResponse){
        preferences!!.edit().putString("data_user_response", gson.toJson(dataUserResponse)).apply()
    }

    fun getDataUser() : DataUserResponse?{
        val str = preferences?.getString("data_user_response", "")
        return if (str.isNullOrEmpty())
            null
        else
            gson.fromJson(str, DataUserResponse::class.java)
    }
}