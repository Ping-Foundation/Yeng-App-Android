package hsm.yeng.syllabus;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hsm.yeng.R;
import hsm.yeng.util.Util;

/**
 * t.
 */
public class BtechSyllabusFragment extends Fragment {
    Spinner semester_spinner;
    ListView listView;
    Util util;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("BTech Syllabus");
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_btech_syllabus, container, false);
        semester_spinner= (Spinner) view.findViewById(R.id.semesterspinner);

        listView= (ListView) view.findViewById(R.id.syllabus_list_btech);
        util=new Util();
        try {
            JSONObject object=new JSONObject(util.loadJSONFromAsset(getActivity(),"ktusyllabusbtech.json"));
            JSONArray syllabus=object.getJSONArray("syllabus");
            ArrayList<syllabusDatamodel> list=new ArrayList<>();
            for (int i=0;i<syllabus.length();i++)
            {
                JSONObject jsonObject=syllabus.getJSONObject(i);
                syllabusDatamodel datamodel=new syllabusDatamodel();
                datamodel.setSubject_name(jsonObject.optString("Subject"));
                datamodel.setSubject_code(jsonObject.optString("code"));
                list.add(datamodel);
            }
            SyllabusAdapter syllabusAdapter=new SyllabusAdapter(getActivity(),list);
            listView.setAdapter(syllabusAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<String> sem=new ArrayList<>();
        sem.add("Semester 1&2");
        sem.add("Semester 3");
        sem.add("Semester 4");
        sem.add("Semester 5");
        sem.add("Semester 6");
        sem.add("Semester 7");
        sem.add("Semester 8");
        Log.e("testing","size"+sem.size());
        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,sem);
        semester_spinner.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),SyllabusSingleViewActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }


}
