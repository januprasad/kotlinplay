package com.github.januprasad.multiscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity :
    AppCompatActivity(),
    MyCommunicator {
    private var mIsDualPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Temporary code, to be replace later
        fragmentA.view?.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

//        val fragmentBView = findViewById<View>(R.id.fragmentB)
//        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun displayDetails(
        title: String,
        description: String,
    ) {
//        if (mIsDualPane) { // If we are in Tablet
//            val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentB?
//            fragmentB?.displayDetails(title, description)
//        } else { // When we are in Smart phone
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("title", title)
//            intent.putExtra("description", description)
//            startActivity(intent)
//        }
    }
}
