package hsm.yeng.rules;

import android.os.Bundle;
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


import hsm.yeng.R;


/**
 *
 */
public class RulesandReg extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("News and Update");
        Log.e("oncreate view..........", "News");
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_rules_regulations, container, false);

        Toolbar toolbar = (Toolbar)v.findViewById(R.id.my_awesome_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout)v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("BTECH"));
        tabLayout.addTab(tabLayout.newTab().setText("MTECH"));
        tabLayout.addTab(tabLayout.newTab().setText("MBA"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)v.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
       /* viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
      */  tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        return v;

    }



    }







