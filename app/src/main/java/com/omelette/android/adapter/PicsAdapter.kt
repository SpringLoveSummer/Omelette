package com.omelette.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omelette.android.R
import com.omelette.android.bean.PicsBean
import com.omelette.android.utils.SpaceItemDecoration
import com.omelette.android.utils.TimeUtil

class PicsAdapter  : PagingDataAdapter<PicsBean.CommentsBean, PicsAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PicsBean.CommentsBean>() {
            override fun areItemsTheSame(
                oldItem: PicsBean.CommentsBean,
                newItem: PicsBean.CommentsBean
            ): Boolean {
                return oldItem.commentID == newItem.commentID
            }

            override fun areContentsTheSame(
                oldItem: PicsBean.CommentsBean,
                newItem: PicsBean.CommentsBean
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        var commentsBean = getItem(position) as PicsBean.CommentsBean
        return if (commentsBean.pics == null) 0 else commentsBean.pics!!.size

    }


    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvAuthor: TextView = itemView.findViewById(R.id.tv_author)
        val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        val content: TextView = itemView.findViewById(R.id.tv_content)
        val likeDes: TextView = itemView.findViewById(R.id.tv_like_des)
        val like: TextView = itemView.findViewById(R.id.tv_like)
        val unLikeDes: TextView = itemView.findViewById(R.id.tv_un_like_des)
        val unLike: TextView = itemView.findViewById(R.id.tv_un_like)
        val tvComment: TextView = itemView.findViewById(R.id.tv_comment)
    }

    class PicViewHolder(itemView: View) : ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.m_max_view)
        val gif: ImageView = itemView.findViewById(R.id.iv_type_gif)
    }

    class PicsViewHolder(itemView: View) : ViewHolder(itemView) {
        val mRv: RecyclerView = itemView.findViewById(R.id.mRv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            1 -> PicViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_picture_only_list, parent, false)
            )
            else -> PicsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_pictures_list, parent, false)
            )
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.tvAuthor.text = repo.commentAuthor
            var textcontent = repo.textContent
            var has = textcontent.endsWith("\n")
            while (has){
                textcontent = textcontent.substring(0,textcontent.length-1)
                has = textcontent.endsWith("\n")
            }
            when (textcontent) {
                "" -> holder.content.visibility = View.GONE
                else -> holder.content.text = textcontent
            }

            holder.like.text = repo.votePositive
            holder.unLike.text = repo.voteNegative
            holder.tvComment.text = "吐槽${repo.subCommentCount}"
            holder.tvTime.text = TimeUtil.getTimeFormatText(
                TimeUtil.stringToDate(
                    repo.commentDate,
                    "yyyy-MM-dd HH:mm:ss"
                )
            )

            if (holder is PicViewHolder) {
                Glide.with(holder.itemView).asBitmap().load(repo.pics?.get(0))
                    .placeholder(R.drawable.icon_default_picture).into(holder.img)
            }
            if (holder is PicsViewHolder) {
                val galleryAdapter = GalleryAdapter()
                holder.mRv.apply {
                    adapter = galleryAdapter
                    layoutManager = GridLayoutManager(holder.itemView.context, 3)
                    addItemDecoration(SpaceItemDecoration(10))
                }

                galleryAdapter.submitList(repo.pics)
            }


        }
    }

}