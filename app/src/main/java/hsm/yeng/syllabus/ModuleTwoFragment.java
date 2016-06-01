package hsm.yeng.syllabus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hsm.yeng.R;
import hsm.yeng.util.Util;


public class ModuleTwoFragment extends Fragment {
    ListView mRecyclerView;
    TextView mModuleNum,mModule_pct;
    Util util;
    public ModuleTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.modulesview, container, false);
        mRecyclerView= (ListView) view.findViewById(R.id.listview_module);
        mModuleNum= (TextView) view.findViewById(R.id.modile_num);
        mModule_pct= (TextView) view.findViewById(R.id.module_percentage);
        util=new Util();
        SyllabusSingleViewActivity activity = (SyllabusSingleViewActivity) getActivity();
        String position = activity.getMyData();

        mModuleNum.setText("Module 2 ");
        JSONObject object= null;
        try {
            ArrayList<syllabusDatamodel> arrayList=new ArrayList<>();
            object = new JSONObject(util.loadJSONFromAsset(getActivity(),"ktusyllabusbtech.json"));
            JSONArray syllabus=object.getJSONArray("syllabus");
            ArrayList<String> arrayList1=new ArrayList<>();
            JSONObject jsonObject1=syllabus.getJSONObject(Integer.parseInt(position));
            JSONArray modules=jsonObject1.getJSONArray("modules");
            JSONObject module=modules.getJSONObject(1);
            mModule_pct.setText(module.optString("mark percentage")+"%");
            JSONArray contents=module.getJSONArray("contents");
            for (int i=0;i<contents.length();i++){
                JSONObject content=contents.getJSONObject(i);
                Log.e("module","content"+content.optString("content"));

                arrayList1.add(content.optString("content"));

            }

            ArrayAdapter adapter=new ArrayAdapter(getActivity(),R.layout.custometectview,arrayList1);
            mRecyclerView.setAdapter(adapter);
            Log.e("modules","success"+arrayList.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }



        // Inflate the layout for this fragment
        return view;
    }

}
