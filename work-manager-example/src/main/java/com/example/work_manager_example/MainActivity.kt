package com.example.work_manager_example

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<FirstFragment>(R.id.fragment_container_view)
//            }
        val fragmentList = supportFragmentManager.fragments
        Log.d("MainApplication", "OnCreate before fragment add FragmentListSize ${fragmentList.size}")
//
        val f = ExampleFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, f)
            .commit()

        Log.d("MainApplication", "OnCreate after fragment add FragmentListSize ${fragmentList.size}")
//        }
    }

    override fun onStart() {
        super.onStart()
        val fragmentList = supportFragmentManager.fragments
        Log.d("MainApplication", "onStart FragmentListSize ${fragmentList.size}")
        supportFragmentManager.fragments.map {
            Log.d("MainApplication", "onStart Fragment Name: ${it.javaClass.simpleName}")
        }
    }

    override fun onResume() {
        super.onResume()
        val fragmentList = supportFragmentManager.fragments
        Log.d("MainApplication", "onResume FragmentListSize ${fragmentList.size}")
        supportFragmentManager.fragments.map {
            Log.d("MainApplication", "onResume Fragment Name: ${it.javaClass.simpleName}")
        }
    }
}
