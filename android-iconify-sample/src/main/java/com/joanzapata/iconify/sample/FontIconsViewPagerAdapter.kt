package com.joanzapata.iconify.sample

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.joanzapata.iconify.IconFontDescriptor
import com.joanzapata.iconify.sample.utils.AndroidUtils.getScreenSize

class FontIconsViewPagerAdapter(private val fonts: Array<Font>) : PagerAdapter() {
    interface FontWithTitle {
        val font: IconFontDescriptor
        val title: String
    }

    override fun getCount(): Int {
        return fonts.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val context = container.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_font, container, false)
        val recyclerView: RecyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        val nbColumns = getScreenSize((context as Activity)).width /
                context.getResources().getDimensionPixelSize(R.dimen.item_width)
        recyclerView.layoutManager = GridLayoutManager(context, nbColumns)
        recyclerView.adapter = IconAdapter(fonts[position].font.characters())
        container.addView(view)
        return view
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fonts[position].title
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, object_: Any) {
        container.removeView(object_ as View?)
    }
}