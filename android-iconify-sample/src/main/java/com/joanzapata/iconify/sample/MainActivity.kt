package com.joanzapata.iconify.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joanzapata.iconify.sample.databinding.ActivityMainBinding

open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)

        // Fill view pager
        binding.viewPager.adapter = FontIconsViewPagerAdapter(Font.values())
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}