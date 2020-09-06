package com.joanzapata.iconify.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindViews
import butterknife.ButterKnife
import com.joanzapata.iconify.Icon

class IconAdapter(private val icons: Array<Icon>) :
    RecyclerView.Adapter<IconAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_icon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val icon = icons[position]
        viewHolder.icon!!.text = MyApplication.getContext().getString(R.string.icon_key, icon.key())
        viewHolder.name!!.text = icon.key()
    }

    override fun getItemCount(): Int {
        return icons.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        @BindViews(R.id.icon)
        var icon: TextView? = null

        @BindViews(R.id.name)
        var name: TextView? = null

        init {
            ButterKnife.bind(this, itemView!!)
        }
    }
}