package com.github.januprasad.multiscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentB : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_b, container, false)

    fun displayDetails(
        title: String,
        description: String,
    ) {
//        txvTitle.text = title
//        txvDescription.text = description
    }
}
