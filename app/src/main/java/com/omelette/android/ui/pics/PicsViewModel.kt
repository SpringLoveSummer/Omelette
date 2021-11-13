package com.omelette.android.ui.pics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omelette.android.bean.PicsBean
import com.omelette.android.repo.Repository

import kotlinx.coroutines.flow.Flow

class PicsViewModel : ViewModel() {

    fun getPagingData(api:String): Flow<PagingData<PicsBean.CommentsBean>> {
        return Repository.getPagingData(api).cachedIn(viewModelScope)
    }

}