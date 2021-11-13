package com.omelette.android.ui.pics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.omelette.android.R
import com.omelette.android.adapter.FooterAdapter
import com.omelette.android.adapter.PicsAdapter
import kotlinx.android.synthetic.main.fragment_layout.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PicsFragment:Fragment(){
    private val viewModel by lazy { ViewModelProvider(this).get(PicsViewModel::class.java) }

    private val repoAdapter = PicsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_layout,container,false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter =
            repoAdapter.withLoadStateFooter(FooterAdapter { repoAdapter.retry() })
        lifecycleScope.launch {
            viewModel.getPagingData("jandan.get_pic_comments").collect { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
      
        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    pb.visibility = View.INVISIBLE
                    rv.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    pb.visibility = View.VISIBLE
                    rv.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    pb.visibility = View.INVISIBLE
                }
            }
        }
    }
}