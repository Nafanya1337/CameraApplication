package com.example.cameraapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cameraapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val fragmentLists by lazy {
        listOf(
            CameraFragment(),
            ListFragment()
        )
    }

    private val tabsName = listOf (
        "Камера",
        "Фотографии"
    )


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = VpAdapter(this, fragmentLists)
        binding.container.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.container) {
            tab, pos ->

            tab.text = tabsName[pos]

        }.attach()


    }
}