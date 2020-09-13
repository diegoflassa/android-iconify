package com.joanzapata.iconify.sample

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.fonts.*
import com.joanzapata.iconify.sample.databinding.ActivityMainBinding
import com.joanzapata.iconify.sample.utils.DecoratedPagerAdapter
import java.util.*
import kotlin.collections.ArrayList


open class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener,
    MenuItem.OnActionExpandListener, SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var selectedTab: TabLayout.Tab
    private lateinit var mMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Setup toolbar
        setSupportActionBar(binding.toolbar)

        // Fill view pager
        val decoratedPagerAdapter = DecoratedPagerAdapter(
            binding.viewPager, FontIconsViewPagerAdapter(
                Font.values()
            )
        )
        binding.viewPager.adapter = decoratedPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.tabs.addOnTabSelectedListener(this)
        selectedTab = binding.tabs.getTabAt(0)!!
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                executeQuery(query)
            }
        }
    }

    private fun executeQuery(query: String){
        val iconsFound = ArrayList<Icon>()
        when (selectedTab.text.toString().toUpperCase(Locale.ROOT)) {
            Font.ENTYPO.name -> {
                if (query.isNotEmpty()) {
                    for (icon in EntypoModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(EntypoModule().characters())
                }
            }
            Font.FONTAWESOME.name -> {
                if (query.isNotEmpty()) {
                    for (icon in FontAwesomeModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(FontAwesomeModule().characters())
                }
            }
            Font.IONICONS.name -> {
                if (query.isNotEmpty()) {
                    for (icon in IoniconsModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(IoniconsModule().characters())
                }
            }
            Font.MATERIAL.name -> {
                if (query.isNotEmpty()) {
                    for (icon in MaterialModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(MaterialModule().characters())
                }
            }
            Font.MATERIALCOMMUNITY.name -> {
                if (query.isNotEmpty()) {
                    for (icon in MaterialCommunityModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(MaterialCommunityModule().characters())
                }
            }
            Font.METEOCONS.name -> {
                if (query.isNotEmpty()) {
                    for (icon in MeteoconsModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(MeteoconsModule().characters())
                }
            }
            Font.SIMPLELINEICONS.name -> {
                if (query.isNotEmpty()) {
                    for (icon in SimpleLineIconsModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(SimpleLineIconsModule().characters())
                }
            }
            Font.TYPICONS.name -> {
                if (query.isNotEmpty()) {
                    for (icon in TypiconsModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(TypiconsModule().characters())
                }
            }
            Font.WEATHERICONS.name -> {
                if (query.isNotEmpty()) {
                    for (icon in WeathericonsModule().characters()) {
                        if (icon.key()!!.contains(query)) {
                            iconsFound.add(icon)
                        }
                    }
                } else {
                    iconsFound.addAll(WeathericonsModule().characters())
                }
            }
        }
        val view = (binding.viewPager.adapter as DecoratedPagerAdapter).getCurrentView()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.adapter = IconAdapter(iconsFound.toTypedArray())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        mMenu = menu

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        searchItem.setOnActionExpandListener(this)
        (searchItem.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
            setOnQueryTextListener(this@MainActivity)
        }
        return true
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        selectedTab = tab!!
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // Do nothing
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // Do nothing
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        onStartSearch()
        return true
    }

    private fun onStartSearch() {
        // Do nothing
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        onCloseSearch()
        return true
    }
    protected open fun getIdentifier(literalId: String?): Int {
        return resources.getIdentifier(
            String.format("android:id/%s", literalId),
            null,
            null
        )
    }
    private fun onCloseSearch() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = mMenu.findItem(R.id.search)
        searchItem.setOnActionExpandListener(this)
        (searchItem.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            val id = getIdentifier("search_src_text")
            val et = findViewById<View>(id) as EditText
            et.setText("")
            setQuery("", false)
            executeQuery("")
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        executeQuery(query!!)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        executeQuery(newText!!)
        return false
    }
}