package hsm.yeng.rules;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import hsm.yeng.R;
import hsm.yeng.util.Util;


public class BtechFragment extends Fragment {
TextView mAdmissionRules;
    TextView mExamRules;
    TextView mEligibilityRules;
    TextView mFeechargedRules;
    TextView mDisciplineRules;

    Util util;
    public BtechFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_btech, container, false);
       /* mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerviewbtech);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);*/

        util=new Util();





        return view;
    }


}
