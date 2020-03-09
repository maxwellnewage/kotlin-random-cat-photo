package com.maxwell.randomcatphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.maxwell.randomcatphoto.fragments.HomeFragment
import com.maxwell.randomcatphoto.fragments.PictureFragment
import com.maxwell.randomcatphoto.network.MeowAPI
import com.maxwell.randomcatphoto.network.models.Cat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnFragmentChange {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment("home")
    }

    fun loadFragment(name:String){
        val transaction = supportFragmentManager.beginTransaction()

        val fragment = if(name == "home"){
            HomeFragment()
        } else {
            PictureFragment()
        }

        transaction.replace(R.id.flContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onChange(name: String) {
        loadFragment(name)
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
