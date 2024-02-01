package com.paveltinnik.app.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paveltinnik.app.R

class ResultItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvEmail = view.findViewById<TextView>(R.id.tv_email)
    val ivPhoto = view.findViewById<ImageView>(R.id.iv_photo)
}