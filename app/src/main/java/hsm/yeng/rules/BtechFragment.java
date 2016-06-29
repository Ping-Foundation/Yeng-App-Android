package hsm.yeng.rules;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
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
    View viewstart;
    Util util;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    LayoutInflater inflat;
    LinearLayout lay;
    ViewGroup contain;
    WebView rules;
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
        /*ProgressDialog dialog=new ProgressDialog(getActivity(),1);*/


         viewstart=inflater.inflate(R.layout.fragment_btech, container, false);
      rules= (WebView) viewstart.findViewById(R.id.webview);
        rules.getSettings().setJavaScriptEnabled(true);
        rules.loadUrl("file:///android_asset/rules.html");
        rules.setVerticalScrollBarEnabled(true);
        return viewstart;




    }
    @Override
    public void onResume() {
        Log.e("btech", "onresume");
        super.onResume();
    }
    private class AsyncTaskRunner extends AsyncTask<View,View,View> {



        @Override
        protected View doInBackground(View... params) {

            return null;
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);

        }

       /*
                 * (non-Javadoc)
                 *
                 * @see android.os.AsyncTask#onPreExecute()
                 */


        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */

    }
}
