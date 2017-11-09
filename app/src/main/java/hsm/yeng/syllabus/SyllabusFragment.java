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

import com.github.barteksc.pdfviewer.PDFView;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.InputStream;
import java.util.ArrayList;

import hsm.yeng.R;
import hsm.yeng.helper.RecyclerViewClickListener;
import hsm.yeng.helper.RecyclerViewTouchListener;
import hsm.yeng.home.HomeActivity;
import hsm.yeng.syllabus.dom.Syllabus;
import hsm.yeng.web.APIClient;
import hsm.yeng.web.SyllabusAPI;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SyllabusFragment extends Fragment {

    HomeActivity homeActivity;
    SyllabusAPI syllabusAPI;
    RecyclerView recyclerView;
    PDFView pdfView;
    Syllabus syllabus;

    ArrayList<String> treeStructure;

    AVLoadingIndicatorView loadingIndicatorView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeActivity=(HomeActivity) getActivity();
        String selectedCourse=homeActivity.getSelectedCourse();
        homeActivity.setTitle(selectedCourse+" Syllabus");
        final View view=inflater.inflate(R.layout.fragment_syllabus,container,false);
        treeStructure=new ArrayList<String>();
        Retrofit retrofit= APIClient.getClient();
        syllabusAPI=retrofit.create(SyllabusAPI.class);
        loadingIndicatorView=(AVLoadingIndicatorView)view.findViewById(R.id.loadingIndicator);
        pdfView=(PDFView)view.findViewById(R.id.pdfView);
        recyclerView=(RecyclerView) view.findViewById(R.id.syllabus_list);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                if((syllabus.getChildrens() != null) && (syllabus.getChildrens().size()>0)){
                    displayChild(syllabus.getChildrens().get(position));
                }else if((syllabus.getFiles() != null) && (syllabus.getFiles().size()>0)){
                    String filePath=getAbsoluteFilePath(syllabus.getFiles().get(position));
                    displayPDF(filePath);
                }else{

                }

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
                    if(pdfView.getVisibility()==View.VISIBLE){
                        pdfView.setVisibility(View.GONE);
                        pdfView.recycle();
                        recyclerView.setVisibility(View.VISIBLE);
                    }else {
                        if (treeStructure.size() > 1) {
                            treeStructure.remove(treeStructure.size() - 1);
                            displayChild(treeStructure.get(treeStructure.size() - 1));
                            treeStructure.remove(treeStructure.size() - 1);
                        }else {
                            getActivity().onBackPressed();
                        }
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
        startAnim();
        Call<Syllabus> syllabusCall= syllabusAPI.getChildById(id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        syllabusCall.enqueue(new Callback<Syllabus>() {
            @Override
            public void onResponse(Call<Syllabus> call, Response<Syllabus> response) {
                Log.e("child size",""+response.body().getChildrens().size());
                syllabus=response.body();
                stopAnim();
                recyclerView.setAdapter(new SyllabusAdapter(syllabus,getContext(),R.layout.syllabusitem));

            }

            @Override
            public void onFailure(Call<Syllabus> call, Throwable t) {
                Log.e("Error",t.toString()+"");
            }
        });
    }

    public void displayPDF(String pdfFilePath){
        recyclerView.setVisibility(View.GONE);
        startAnim();
        Call<ResponseBody> downLoadCall=syllabusAPI.downloadSyllabus(pdfFilePath);
        downLoadCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    InputStream inputStream=response.body().byteStream();
                    stopAnim();
                    pdfView.setVisibility(View.VISIBLE);
                    pdfView.fromStream(inputStream).load();
                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

    String getAbsoluteFilePath(String fileName){
        String totalURL="";
        String id=treeStructure.get(treeStructure.size()-1);
        String idSplitted[]=id.split("_");
        if(idSplitted.length>1){
            for(int i=0;i<idSplitted.length;i++){
                totalURL+=(idSplitted[i]+"/");
            }
        }
        totalURL=totalURL+fileName+".pdf";

        return  totalURL;
    }


    public void startAnim(){
        loadingIndicatorView.setVisibility(View.VISIBLE);
        loadingIndicatorView.show();
    }

    public void stopAnim(){
        loadingIndicatorView.setVisibility(View.GONE);
        loadingIndicatorView.hide();
    }


}
