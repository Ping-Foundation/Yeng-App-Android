package hsm.yeng.syllabus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hsm.yeng.R;
import hsm.yeng.helper.RecyclerViewClickListener;
import hsm.yeng.helper.RecyclerViewTouchListener;
import hsm.yeng.home.HomeActivity;
import hsm.yeng.syllabus.dom.Syllabus;
import hsm.yeng.web.APIClient;
import hsm.yeng.web.SyllabusAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SyllabusFragment extends Fragment {

    HomeActivity homeActivity;
    SyllabusAPI syllabusAPI;
    RecyclerView recyclerView;
    Syllabus syllabus;

    ArrayList<String> treeStructure;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeActivity=(HomeActivity) getActivity();
        String selectedCourse=homeActivity.getSelectedCourse();
        homeActivity.setTitle(selectedCourse+" Syllabus");
        View view=inflater.inflate(R.layout.fragment_syllabus,container,false);
        treeStructure=new ArrayList<String>();
        Retrofit retrofit= APIClient.getClient();
        syllabusAPI=retrofit.create(SyllabusAPI.class);
        recyclerView=(RecyclerView) view.findViewById(R.id.syllabus_list);
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
        view.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (treeStructure.size() > 1) {
                        treeStructure.remove(treeStructure.size() - 1);
                        displayChild(treeStructure.get(treeStructure.size() - 1));
                        treeStructure.remove(treeStructure.size() - 1);

                    } else {
                        return false;
                    }
                    return true;
                }
                return true;
            }

        });
        displayChild(selectedCourse);
        return view;

    }

    public void displayChild(String id){
        treeStructure.add(id);
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


}
