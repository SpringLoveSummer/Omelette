package com.omelette.android.repo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omelette.android.api.OmeletService
import com.omelette.android.bean.NewsBean
import java.lang.Exception

class NewsPagingSource() : PagingSource<Int, NewsBean.PostsBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsBean.PostsBean> {
        return try {
            val page = params.key ?: 1 // set page 1 as default
            val repoResponse = OmeletService.create().searchNews( page)
            val repoItems = repoResponse.posts
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsBean.PostsBean>): Int? = null

}