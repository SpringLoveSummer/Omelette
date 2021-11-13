package com.omelette.android.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omelette.android.bean.NewsBean
import com.omelette.android.bean.PicsBean
import com.omelette.android.repo.data.NewsPagingSource
import com.omelette.android.repo.data.PicsPagingSource
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 50

    fun getPagingData(): Flow<PagingData<NewsBean.PostsBean>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource() }
        ).flow
    }

    fun getPagingData( api:String): Flow<PagingData<PicsBean.CommentsBean>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { PicsPagingSource(api) }
        ).flow
    }

}