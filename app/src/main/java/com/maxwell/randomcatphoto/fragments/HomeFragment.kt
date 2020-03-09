package com.maxwell.randomcatphoto.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maxwell.randomcatphoto.OnFragmentChange
import com.maxwell.randomcatphoto.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    var onFragmentChange : OnFragmentChange? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onFragmentChange = context as OnFragmentChange
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        btCatPicture.setOnClickListener {
            onFragmentChange?.onChange("picture")
        }
    }
}