package com.github.januprasad.multiscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_a, container, false)

        setupRecyclerView(rootView)

        return rootView
    }

    private fun setupRecyclerView(rootView: View) {
//        val recyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView
//
//        val adapter = RecyclerAdapter(context, DataProvider.data)
//        recyclerView.adapter = adapter
//
//        val manager = LinearLayoutManager(activity)
//        manager.orientation = LinearLayoutManager.VERTICAL
//        recyclerView.layoutManager = manager
    }
}
