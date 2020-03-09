package com.maxwell.randomcatphoto.network

import com.maxwell.randomcatphoto.network.models.Cat
import retrofit2.Call
import retrofit2.http.GET

interface MeowAPI {
    @GET("meow")
    fun getCatPicture() : Call<Cat>
}