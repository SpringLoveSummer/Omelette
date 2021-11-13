package com.omelette.android.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omelette.android.bean.NewsBean
import com.omelette.android.repo.Repository
import kotlinx.coroutines.flow.Flow

class NewsViewModel : ViewModel() {

    fun getPagingData(): Flow<PagingData<NewsBean.PostsBean>> {
        return Repository.getPagingData().cachedIn(viewModelScope)
    }

}