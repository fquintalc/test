package com.fquintal.test.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class UserLoggedIn constructor(
    val username: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("access_token") val accessToken: String
) : Parcelable{
}