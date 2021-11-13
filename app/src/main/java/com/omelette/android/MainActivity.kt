package com.omelette.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.omelette.android.ui.news.NewsFragment
import com.omelette.android.ui.pics.PicsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var  mediator: TabLayoutMediator
    val tabNames = listOf<String>("新鲜事", "无聊图", "随手拍")
    private var onPageChangeCallback: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            tabLayout.selectTab(tabLayout.getTabAt(position))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initTabLayout()
    }

    private fun initToolbar() {
        toolbar.title = "煎蛋"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }




    private fun initTabLayout() {
        viewpager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        viewpager2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return getTabFragment(position)
            }

            override fun getItemCount(): Int {
                return tabNames.size
            }
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            var str = ""
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager2.currentItem = tab.position
                Log.e("TAG", "onTabSelected")
                str = tab.text.toString()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        TabLayoutMediator(tabLayout, viewpager2, true) { tab, position -> tab.text = tabNames[position] }.attach()
        viewpager2.registerOnPageChangeCallback(onPageChangeCallback)

    }


    fun getTabFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewsFragment()
            1 -> PicsFragment.newInstance("jandan.get_pic_comments")
            else -> PicsFragment.newInstance("jandan.get_ooxx_comments")
        }
    }

    override fun onDestroy() {
        mediator.detach()
        viewpager2.unregisterOnPageChangeCallback(onPageChangeCallback)
        super.onDestroy()
    }


}