package hsm.yeng.syllabus;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hsm.yeng.R;

/**
 * t.
 */
public class BtechSyllabusFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("BTech Syllabus");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_btech_syllabus, container, false);
    }


}
