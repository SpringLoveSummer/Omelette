package com.omelette.android.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omelette.android.R
import com.omelette.android.ui.PictureDetailActivity
import kotlinx.android.synthetic.main.gallery_cell.view.*

class GalleryAdapter: ListAdapter<String, MyViewHolder>(DIFFCALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.gallery_cell,
                parent,
                false
            )
        )
        holder.itemView.setOnClickListener {
            val intent = Intent(parent.context, PictureDetailActivity::class.java).apply {
                putStringArrayListExtra("PHOTO_LIST", ArrayList(currentList))
                putExtra("PHOTO_POSITION", holder.adapterPosition)
            }
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getItem(position))
            .placeholder(R.drawable.ic_photo_gray_24dp)
            .into(holder.itemView.imageView)
    }

    object DIFFCALLBACK: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

}


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)