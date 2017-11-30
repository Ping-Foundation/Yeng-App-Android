package hsm.yeng.join;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import hsm.yeng.R;

/**
 *
 */
public class JoinWithUsFragment extends Fragment {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Join With Us");
        // Inflate the layout for this fragment
        Log.e("oncreate view..........","Join with us");
        View v= inflater.inflate(R.layout.fragment_join_with_us, container, false);
        viewPagerAdapter=new ViewPagerAdapter(getFragmentManager());
        viewPager=v.findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);
        return v;
    }







}
