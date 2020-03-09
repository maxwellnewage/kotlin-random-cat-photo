package com.maxwell.randomcatphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maxwell.randomcatphoto.network.MeowAPI
import com.maxwell.randomcatphoto.network.models.Cat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCat()
    }

    fun getCat(){
        val loader = Retrofit.Builder()
            .baseUrl("https://aws.random.cat/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = loader.create(MeowAPI::class.java)

        service.getCatPicture().enqueue(object : Callback<Cat> {
            override fun onFailure(call: Call<Cat>, t: Throwable) {

            }

            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {

            }
        })
    }
}
