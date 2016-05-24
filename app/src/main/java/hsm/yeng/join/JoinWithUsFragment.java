package hsm.yeng.join;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hsm.yeng.R;

/**
 *
 */
public class JoinWithUsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Join With Us");
        // Inflate the layout for this fragment
        Log.e("oncreate view..........","Join with us");
        return inflater.inflate(R.layout.fragment_join_with_us, container, false);
    }






}
