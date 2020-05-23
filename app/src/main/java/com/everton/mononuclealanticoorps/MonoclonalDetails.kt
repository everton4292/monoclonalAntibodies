package com.everton.mononuclealanticoorps

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.everton.mononuclealanticoorps.fragments.FragmentDefinition
import com.everton.mononuclealanticoorps.fragments.FragmentInformationsOfUse
import com.everton.mononuclealanticoorps.fragments.FragmentOtherInfos
import com.everton.mononuclealanticoorps.fragments.FragmentPreMedication
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.util.*

class MonoclonalDetails : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monoclonal_details)
        val toolbar: Toolbar
        toolbar =
            findViewById<View>(R.id.mToolBar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Monoclonal Antibodies Names"
        toolbar.setNavigationIcon(android.R.drawable.ic_media_previous)
        viewPager = findViewById<View>(R.id.tab_viewPager) as ViewPager
        if (viewPager != null) {
            setupViewPager(viewPager!!)
        }
        val tabLayout = findViewById<View>(R.id.tabLayout) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private inner class ViewPagerAdapter internal constructor(manager: FragmentManager) :
        FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragmentList: MutableList<Fragment> =
            ArrayList()
        private val mFragmentTitleList: MutableList<String> =
            ArrayList()

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(FragmentDefinition(), "Definition")
        adapter.addFrag(FragmentInformationsOfUse(), "Use Information")
        adapter.addFrag(FragmentPreMedication(), "Pre medication")
        adapter.addFrag(FragmentOtherInfos(), "Other information")
        viewPager.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}