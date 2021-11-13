package com.omelette.android.bean

import com.google.gson.annotations.SerializedName

data class NewsBean(
    val status: String, val pages: Int,
    @SerializedName("count_total") val countTotal: Int,
    val count: Int, val posts: List<PostsBean>
) {
    data class PostsBean(
        val id: Int,
        val url: String,
        val title: String,
        val excerpt: String,
        val date: String,
        val author: AuthorBean,
        @SerializedName("comment_count") val commentCount: Int,
        @SerializedName("comment_status") val commentStatus: String,
        @SerializedName("custom_fields") val customFiledBean: CustomFieldsBean
    )

    data class AuthorBean(val id: Int, val nickname: String, val description: String)

    data class CustomFieldsBean(@SerializedName("thumb_c")val thumbC: List<String>)
}