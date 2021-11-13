package com.omelette.android.bean

import com.google.gson.annotations.SerializedName

data class PicsBean(val status:String, @SerializedName("current_page") val page: Int,
                     @SerializedName("total_comments") val totalComment: Int,
                     @SerializedName("page_count") val pageCount: Int, val count:Int, val comments:List<CommentsBean>){

    data class CommentsBean(@SerializedName("comment_ID") val commentID: String,
                            @SerializedName("comment_post_ID") val commentPostID: String,
                            @SerializedName("comment_author") val commentAuthor: String,
                            @SerializedName("comment_date") val commentDate: String,
                            @SerializedName("comment_date_gmt") val commentDateGmt: String,
                            @SerializedName("comment_content") val commentContent: String?,
                            @SerializedName("vote_positive") val votePositive: String,
                            @SerializedName("vote_negative") val voteNegative: String,
                            @SerializedName("sub_comment_count") val subCommentCount: String,
                            @SerializedName("text_content") val textContent: String,
                            val  pics: List<String>?)
}