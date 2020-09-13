package com.joanzapata.iconify.sample.utils

import android.database.DataSetObserver
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

@Suppress("Unused")
open class DecoratedPagerAdapter() : PagerAdapter() {
    private var pagerAdapter: PagerAdapter? = null

    /** Stores the views in the actual position of the adapter.  */
    private var instantiatedViewsSparsedArray: SparseArray<View>? = null
    private var viewPager: ViewPager? = null

    constructor (viewPager: ViewPager?, decoratedPagerAdapter: PagerAdapter) : this() {
        pagerAdapter = decoratedPagerAdapter
        decoratedPagerAdapter.registerDataSetObserver(object : DataSetObserver() {
            override fun onChanged() {
                notifyDataSetChanged()
            }
        })
        this.viewPager = viewPager
        instantiatedViewsSparsedArray = SparseArray<View>()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val viewRoot = pagerAdapter?.instantiateItem(container, position)
        val viewPager = container as ViewPager
        val childCount = viewPager.childCount
        for (i in 0 until childCount) {
            val child: View = viewPager.getChildAt(i)
            if (isViewFromObject(child, viewRoot!!)) {
                instantiatedViewsSparsedArray!!.put(position, child)
                break
            }
        }
        return viewRoot!!
    }

    override fun getItemPosition(`object`: Any): Int {
        val childCount = viewPager!!.childCount
        for (i in 0 until childCount) {
            val child: View = viewPager!!.getChildAt(i)
            if (isViewFromObject(child, `object`)) {
                return i
            }
        }
        return POSITION_NONE
    }

    /**
     * @param position the position in the adapter of the view we are interested in.
     * @return The view at position `position` in the adapter or null if it has not been initialized.
     */
    private fun getViewAtPosition(position: Int): View? {
        return instantiatedViewsSparsedArray!![position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pagerAdapter?.getPageTitle(position)!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        pagerAdapter?.destroyItem(container, position, `object`)
        instantiatedViewsSparsedArray!!.remove(position)
    }

    override fun isViewFromObject(view: View, object_: Any): Boolean {
        return pagerAdapter!!.isViewFromObject(view, object_)
    }

    override fun getCount(): Int {
        return pagerAdapter!!.count
    }

    /**
     * The method is deliberately package-protected.
     *
     * @return The pager adapter that is wrapped with current view tracking pager adapter.
     */
    fun getPagerAdapter(): PagerAdapter? {
        return pagerAdapter
    }

    /** Returns the index'th child of the decorated [ViewPager]  */
    open fun getCurrentView(): View? {
        return getViewAtPosition(viewPager?.currentItem!!)
    }
}