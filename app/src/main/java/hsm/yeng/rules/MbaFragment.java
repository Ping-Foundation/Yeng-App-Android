package hsm.yeng.rules;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hsm.yeng.R;


public class MbaFragment extends Fragment {
View view;
    TextView textView1,textview2,textview3;
    LayoutInflater inflate;
    ViewGroup container1;
    public MbaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
ProgressDialog dialog=new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.show();

              view =inflater.inflate(R.layout.fragment_mba, container, false);

        textView1= (TextView) view.findViewById(R.id.textview1);
        textview2= (TextView) view.findViewById(R.id.textview2);
        textView1.setText(getResources().getString(R.string.mbaRules));
        textview2.setText(getResources().getString(R.string.mbaRules2));
        dialog.dismiss();
         /* mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerviewbtech);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);*/







        return view;
    }


}
