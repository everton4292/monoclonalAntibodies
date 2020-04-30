package com.everton.mononuclealanticoorps;
import com.everton.mononuclealanticoorps.R;
import com.everton.mononuclealanticoorps.fragments.FragmentDefinition;
import com.everton.mononuclealanticoorps.fragments.FragmentInformationsOfUse;
import com.everton.mononuclealanticoorps.fragments.FragmentOtherInfos;
import com.everton.mononuclealanticoorps.fragments.FragmentPreMedication;
import com.google.android.material.tabs.TabLayout;

import android.R.drawable.*;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


public class MonoclonalDetails extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monoclonal_details);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.mToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Monoclonal Antibodies Names");

        toolbar.setNavigationIcon(android.R.drawable.ic_media_previous);

        viewPager = (ViewPager) findViewById(R.id.tab_viewPager);

        if (viewPager != null){
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentDefinition(), "Definition");
        adapter.addFrag(new FragmentInformationsOfUse(), "Use Informations");
        adapter.addFrag(new FragmentPreMedication(), "Pre medication");
        adapter.addFrag(new FragmentOtherInfos(), "Other infos");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
