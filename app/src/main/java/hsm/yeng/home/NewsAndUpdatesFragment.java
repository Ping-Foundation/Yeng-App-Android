package hsm.yeng.home;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 *
 */
public class NewsAndUpdatesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("News and Updates");
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_news_and_updates, container, false);
        return v;
    }






}
