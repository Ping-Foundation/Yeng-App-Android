package hsm.yeng.syllabus;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hsm.yeng.R;

/**
 *
 */
public class MtechSyllabusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Mtech Syllabus");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mtech_syllabus, container, false);
    }


}
