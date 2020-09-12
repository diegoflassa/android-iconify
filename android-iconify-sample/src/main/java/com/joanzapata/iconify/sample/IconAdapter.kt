package com.joanzapata.iconify.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.sample.databinding.ItemIconBinding

class IconAdapter(private val icons: Array<Icon>) :
    RecyclerView.Adapter<IconAdapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val icon = icons[position]
        viewHolder.binding.icon.text = SampleApplication.getContext().getString(R.string.icon_key, icon.key())
        viewHolder.binding.name.text = icon.key()
    }

    override fun getItemCount(): Int {
        return icons.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val binding = ItemIconBinding.bind(itemView!!)
    }
}