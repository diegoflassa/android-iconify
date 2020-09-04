package com.joanzapata.iconify.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.tabs.TabLayout

open class MainActivity : AppCompatActivity() {
    @BindView(R.id.tabs)
    var tabLayout: TabLayout? = null

    @BindView(R.id.toolbar)
    var toolbar: Toolbar? = null

    @BindView(R.id.viewPager)
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        // Setup toolbar
        setSupportActionBar(toolbar)

        // Fill view pager
        viewPager?.adapter = FontIconsViewPagerAdapter(Font.values())
        tabLayout?.setupWithViewPager(viewPager)
    }
}