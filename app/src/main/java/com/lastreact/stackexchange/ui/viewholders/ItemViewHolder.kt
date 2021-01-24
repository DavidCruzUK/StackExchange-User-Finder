package com.lastreact.stackexchange.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.stackexchange.databinding.RowItemBinding

class ItemViewHolder(private val binding: RowItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        user: UserItem,
        listener: (id: Int) -> Unit
    ) {
        with(binding) {
            userNameTv.text = user.displayName
            reputationTv.text = user.reputation.toString()
        }
        binding.root.setOnClickListener { listener(user.userId) }
    }
}