package hsm.yeng.rules;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hsm.yeng.R;


public class MtechFragment extends Fragment {
View view;
    public MtechFragment() {
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
        Log.e("mtech","csadasdsad");

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        view =inflater.inflate(R.layout.fragment_mtech, container, false);

        pDialog.dismiss();
/* mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerviewbtech);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);*/







        return view;

         }

    @Override
    public void onResume() {
        Log.e("mtech","onresume");
        super.onResume();
    }
}
