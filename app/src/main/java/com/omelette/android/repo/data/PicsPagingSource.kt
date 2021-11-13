package com.omelette.android.repo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omelette.android.api.OmeletService
import com.omelette.android.bean.PicsBean
import java.lang.Exception

class PicsPagingSource(private val api:String) : PagingSource<Int, PicsBean.CommentsBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicsBean.CommentsBean> {
        return try {
            val page = params.key ?: 1
            val repoResponse = OmeletService.create().searchPlaces(api, page)
            val repoItems = repoResponse.comments
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PicsBean.CommentsBean>): Int? = null

}