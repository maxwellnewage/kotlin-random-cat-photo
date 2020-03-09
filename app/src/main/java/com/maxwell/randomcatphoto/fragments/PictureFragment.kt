package com.maxwell.randomcatphoto.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.maxwell.randomcatphoto.R
import com.maxwell.randomcatphoto.network.MeowAPI
import com.maxwell.randomcatphoto.network.models.Cat
import kotlinx.android.synthetic.main.fragment_picture.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PictureFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCat()

        btCatOtherPicture.setOnClickListener {
            getCat()
        }
    }

    fun failPictureLoad(){
        btCatOtherPicture.text = activity!!.resources.getText(R.string.button_title_cat_fail)

        pbLoading.visibility = View.GONE
    }

    fun getCat(){
        pbLoading.visibility = View.VISIBLE

        val loader = Retrofit.Builder()
            .baseUrl("https://aws.random.cat/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = loader.create(MeowAPI::class.java)

        service.getCatPicture().enqueue(object : Callback<Cat> {
            override fun onFailure(call: Call<Cat>, t: Throwable) {
                failPictureLoad()
            }

            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                Glide.with(activity!!)
                    .load(response.body()?.file)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            failPictureLoad()

                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            btCatOtherPicture.text = activity!!.resources.getText(R.string.button_title_cat_other)

                            pbLoading.visibility = View.GONE

                            return false
                        }
                    })
                    .override(view!!.width)
                    .into(ivCatPicture)
            }
        })
    }
}