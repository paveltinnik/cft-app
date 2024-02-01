package com.paveltinnik.app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paveltinnik.app.R
import com.paveltinnik.app.domain.entity.Person
import com.squareup.picasso.Picasso

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id?.value == newItem.id?.value
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_person,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        try {
            val person = differ.currentList[position]

            holder.itemView.findViewById<TextView>(R.id.tv_name).text = person.name?.first
            holder.itemView.findViewById<TextView>(R.id.tv_email).text = person.email
            Glide.with(holder.itemView).load(person.picture?.medium).into(holder.itemView.findViewById(R.id.iv_photo))

            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(person) }
            }
        } catch (e: Exception) {
            Log.e("Error in recycler view", e.toString())
        }
    }

    private var onItemClickListener: ((Person) -> Unit)? = null

    fun setOnItemClickListener(listener: (Person) -> Unit) {
        onItemClickListener = listener
    }
}