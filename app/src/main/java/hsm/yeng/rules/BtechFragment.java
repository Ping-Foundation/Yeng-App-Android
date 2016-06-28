package hsm.yeng.rules;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    View view;
    Util util;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    public BtechFragment() {
        // Required empty public constructor

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("dasdas", "btech");

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

               pDialog.dismiss();
                view =inflater.inflate(R.layout.fragment_btech, container, false);








        return view;
    }
    @Override
    public void onResume() {
        Log.e("btech", "onresume");
        super.onResume();
    }

}
