package com.example.catapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.catapp.cat.view.CatFragment

class MainActivity : AppCompatActivity() {

    private val catFragment = CatFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpCatFragment()
    }

    private fun setUpCatFragment() {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.root_frame_layout, catFragment)
            .commit()
    }
}