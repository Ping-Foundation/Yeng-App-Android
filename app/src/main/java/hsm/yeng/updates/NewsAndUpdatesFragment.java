package hsm.yeng.updates;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import hsm.yeng.R;


/**
 *
 */
public class NewsAndUpdatesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<NewsDataModel> newsData;
    private GestureDetectorCompat detector;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("News and Updates");
        Log.e("oncreate view..........", "News");
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_news_and_updates, container, false);
        detector = new GestureDetectorCompat(getActivity(), new RecyclerViewOnGestureListener());
        mRecyclerView = (RecyclerView) v.findViewById(R.id.news_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        newsData=new ArrayList<NewsDataModel>();

        final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.yengapp.com/getnews", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                try {
                    JSONArray jsonArray = new JSONArray(new String(responseBody));
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        NewsDataModel newsDataModel=new NewsDataModel();
                        newsDataModel.setId(jsonObject.getString("_id"));
                        Log.e("fgfg",jsonObject.getString("_id"));
                        newsDataModel.setNewsHead(jsonObject.getString("Tittle"));
                        newsDataModel.setNewsContent(jsonObject.getString("News"));
                        newsDataModel.setNewsDate(jsonObject.getString("DisplayDate").substring(0,10));
                        newsData.add(newsDataModel);


                    }
                    pDialog.dismiss();
                    mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                        @Override
                        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                            detector.onTouchEvent(e);
                            return false;
                        }

                        @Override
                        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                        }

                        @Override
                        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                        }
                    });
                    mAdapter = new NewsAdapter(newsData);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                pDialog.dismiss();
                Toast.makeText(getContext(), "Please check your Internet connection", Toast.LENGTH_SHORT).show();
            }
        });
       // mAdapter = new NewsAdapter(newsData);
       // mRecyclerView.setAdapter(mAdapter);

        return v;
    }



    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            int position = mRecyclerView.getChildPosition(view);

            // handle single tap
            //Log.e("ggggg",Integer.toString(position));
            String id=newsData.get(position).getId();
            String heading=newsData.get(position).getNewsHead();
            String content=newsData.get(position).getNewsContent();
            String date=newsData.get(position).getNewsDate();
            Intent intent=new Intent(getActivity(),DetailNewsActivity.class);
            intent.putExtra("id",id);
           /* intent.putExtra("head",heading);
            intent.putExtra("con",content);
            intent.putExtra("date",date);*/
            startActivity(intent);

            return super.onSingleTapConfirmed(e);
        }

        public void onLongPress(MotionEvent e) {
            View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            int position = mRecyclerView.getChildPosition(view);

            // handle long press

            super.onLongPress(e);
        }
    }


}
