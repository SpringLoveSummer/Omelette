package com.omelette.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omelette.android.R
import com.omelette.android.bean.NewsBean
import com.omelette.android.utils.TimeUtil

class NewsAdapter : PagingDataAdapter<NewsBean.PostsBean, NewsAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<NewsBean.PostsBean>() {
            override fun areItemsTheSame(oldItem: NewsBean.PostsBean, newItem: NewsBean.PostsBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsBean.PostsBean, newItem: NewsBean.PostsBean): Boolean {
                return oldItem == newItem
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.iv_img)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val author: TextView = itemView.findViewById(R.id.tv_author)
        val time: TextView = itemView.findViewById(R.id.tv_time)
        val comment: TextView = itemView.findViewById(R.id.tv_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.title.text = repo.title
            holder.author.text = repo.author.nickname
            holder.comment.text = "${repo.commentCount}评论"
            holder.time.text = TimeUtil.getTimeFormatText(TimeUtil.stringToDate(repo.date,"yyyy-MM-dd HH:mm:ss"))
            Glide.with(holder.itemView).load(repo.customFiledBean.thumbC?.get(0)).into(holder.img)
        }
    }

}