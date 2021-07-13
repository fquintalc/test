package com.fquintal.test.remote.service

import com.fquintal.model.ListMovement
import com.fquintal.model.Login
import com.fquintal.test.remote.responses.DataUserResponse
import com.fquintal.test.remote.responses.UserLoggedIn
import retrofit2.Call
import retrofit2.http.*

interface FinerioService {

    @POST("api/login")
    fun login(@Body login: Login) : Call<UserLoggedIn>

    @GET("api/me")
    fun getDataUser(@Header("Authorization") authorization : String) : Call<DataUserResponse>

    @GET("api/users/{userId}/movements")
    fun getMovements(
        @Header("Authorization") authorization : String,
        @Path("userId")userId : String,
        @Query("deep") deep : Boolean,
        @Query("offset") offset : Int,
        @Query("max") max : Int,
        @Query("includeCharges") includeCharges : Boolean,
        @Query("includeDuplicates") includeDuplicates : Boolean
        ) : Call<ListMovement>
}