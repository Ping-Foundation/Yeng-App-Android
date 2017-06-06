package hsm.yeng.join;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hsm.yeng.R;

/**
 *
 */
public class JoinWithUsFragment extends Fragment {

TextView beacon;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Join With Us");
        // Inflate the layout for this fragment
        Log.e("oncreate view..........","Join with us");
        View v= inflater.inflate(R.layout.fragment_join_with_us, container, false);
        beacon= (TextView) v.findViewById(R.id.beacon);
        beacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yeng.in"));
                startActivity(browserIntent);
            }
        });
        return v;
    }






}
