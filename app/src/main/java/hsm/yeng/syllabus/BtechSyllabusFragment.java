package hsm.yeng.syllabus;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import hsm.yeng.R;
import hsm.yeng.helper.RecyclerViewClickListener;
import hsm.yeng.helper.RecyclerViewTouchListener;
import hsm.yeng.syllabus.dom.Syllabus;
import hsm.yeng.web.APIClient;
import hsm.yeng.web.SyllabusAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 */
public class BtechSyllabusFragment extends Fragment {

    String TAG="BtechSyllabusFragment";
    Spinner semester_spinner;
    ArrayList<String> semArrayList;

    SyllabusAPI syllabusAPI;
    RecyclerView recyclerView;
    ArrayList<String> searchIDs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("BTech Syllabus");
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_btech_syllabus, container, false);
        semester_spinner= (Spinner) view.findViewById(R.id.semesterspinner);
         semArrayList=getSemesterValues();
        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,semArrayList);
        semester_spinner.setAdapter(adapter);
        semester_spinner.setSelection(0);
        Retrofit retrofit= APIClient.getClient();
        syllabusAPI=retrofit.create(SyllabusAPI.class);
        recyclerView=(RecyclerView) view.findViewById(R.id.syllabus_list_btech);
        semester_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("onItemSelected","Pos:"+position);
                searchIDs.clear();
                if(position==0){
                    displayChild("S1");
                }else{
                    displayChild("S"+(position+2));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchIDs=new ArrayList<String>();

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("Recyler-onClick","Pos:"+position);
                displayChild(syllabus.getChildrens().get(position));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("Key Listener", "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.i("Key Listener", "onKey Back listener is working!!!");
                    if(searchIDs.size()>1){
                        searchIDs.remove(searchIDs.size()-1);
                        displayChild(searchIDs.get(searchIDs.size()-1));
                        searchIDs.remove(searchIDs.size()-1);

                    }else if(searchIDs.size()<=1){
                        searchIDs.clear();
                        return false;
                    }else{
                        return false;
                    }

                    return true;
                }
                return false;
            }
        });
        return view;
    }

    private ArrayList<String> getSemesterValues(){
        ArrayList<String> sem=new ArrayList<>();
        sem.add("Semester 1&2");
        sem.add("Semester 3");
        sem.add("Semester 4");
        sem.add("Semester 5");
        sem.add("Semester 6");
        sem.add("Semester 7");
        sem.add("Semester 8");

        return sem;
    }
    Syllabus syllabus=null;
    public void displayChild(String id){
        searchIDs.add(id);
        Call<Syllabus> syllabusCall= syllabusAPI.getChildById(id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        syllabusCall.enqueue(new Callback<Syllabus>() {
            @Override
            public void onResponse(Call<Syllabus> call, Response<Syllabus> response) {
                Log.e("child size",""+response.body().getChildrens().size());
                syllabus=response.body();
                recyclerView.setAdapter(new SyllabusAdapter(syllabus,getContext(),R.layout.syllabusitem));

            }

            @Override
            public void onFailure(Call<Syllabus> call, Throwable t) {
                Log.e("error",t.toString()+"");
            }
        });
    }

    public void openFile(String fileName){

    }




}
