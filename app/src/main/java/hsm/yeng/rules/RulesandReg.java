package hsm.yeng.rules;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.app.ActionBar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import hsm.yeng.R;


/**
 *
 */
public class RulesandReg extends Fragment {

      ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_rules_regulations, container, false);

       /* Toolbar toolbar = (Toolbar)v.findViewById(R.id.my_awesome_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        */
        Log.e("loading............","dsgvdfgdfgggg");
        TabLayout tabLayout = (TabLayout)v.findViewById(R.id.tab_layout);


        tabLayout.addTab(tabLayout.newTab().setText("MBA"));
       tabLayout.addTab(tabLayout.newTab().setText("MTECH"));
        tabLayout.addTab(tabLayout.newTab().setText("BTECH"));
       tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        getActivity().setTitle("Rules and Regulations");
        final ViewPager viewPager = (ViewPager)v.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());


       viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
       tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        /*FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
              getActivity().getSupportFragmentManager(), FragmentPagerItems.with(getActivity())
                .add("MBA",MbaFragment.class)
                .add("MTECH",MtechFragment.class)
                .add("BTECH",BtechFragment.class)
                .create());
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout)v.findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);*/

        return v;

    }


    }







