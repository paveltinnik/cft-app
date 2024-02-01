package com.paveltinnik.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.paveltinnik.app.R
import com.paveltinnik.app.domain.entity.Person
import com.squareup.picasso.Picasso

class ResultListAdapter: ListAdapter<Person, ResultItemViewHolder>(ResultItemDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ResultItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ResultItemViewHolder, position: Int) {
        val resultItem = getItem(position)
        viewHolder.tvName.text = resultItem.name?.first
        viewHolder.tvEmail.text = resultItem.email
        Picasso.get().load(resultItem.picture?.thumbnail).into(viewHolder.ivPhoto)
    }
}