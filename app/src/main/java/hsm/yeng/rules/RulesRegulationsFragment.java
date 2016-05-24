package hsm.yeng.rules;

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
public class RulesRegulationsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Rules and Regulations");
        Log.e("oncreate view..........", "Rules");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rules_regulations, container, false);
    }





}
