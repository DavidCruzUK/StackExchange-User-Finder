package com.lastreact.stackexchange.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.stackexchange.databinding.RowItemBinding
import com.lastreact.stackexchange.ui.viewholders.ItemViewHolder
import kotlin.properties.Delegates

class UserListAdapter(
    items: List<UserItem> = emptyList(),
    private val listener: (id: Int) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    var items: List<UserItem> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: RowItemBinding =
            RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size.sizeUntil20Max()
    }

    private fun Int.sizeUntil20Max(): Int = when {
        this in 0 until 20 -> this
        this > 20 -> 20
        else -> 0
    }


}