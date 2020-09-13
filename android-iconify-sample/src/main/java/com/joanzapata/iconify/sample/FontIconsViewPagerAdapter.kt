package com.joanzapata.iconify.sample

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.joanzapata.iconify.IconFontDescriptor
import com.joanzapata.iconify.sample.databinding.ItemFontBinding
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
        val binding = ItemFontBinding.inflate(LayoutInflater.from(context), container, false)
        val nbColumns = getScreenSize((context as Activity)).width /
                context.getResources().getDimensionPixelSize(R.dimen.item_width)
        binding.recyclerView.layoutManager = GridLayoutManager(context, nbColumns)
        binding.recyclerView.adapter = IconAdapter(fonts[position].font.characters())
        container.addView(binding.root)
        return binding.root
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fonts[position].title
    }

    override fun isViewFromObject(view: View, object_: Any): Boolean {
        return view === object_
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}