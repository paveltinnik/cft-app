package com.paveltinnik.app.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.paveltinnik.app.domain.entity.Person

class ResultItemDiffCallback: DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}