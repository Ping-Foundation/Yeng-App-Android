package hsm.yeng.rules;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import hsm.yeng.R;


/**
 *
 */
public class Rules1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("News and Update");
        Log.e("oncreate view..........", "News");
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_btech, container, false);
       /* rules.getSettings().setJavaScriptEnabled(true);
        rules.loadUrl("file:///android_asset/rules.html");
        rules.setVerticalScrollBarEnabled(true);*/
        AssetManager mgr = getContext().getAssets();
        try {
            InputStream in = mgr.open("rules",AssetManager.ACCESS_BUFFER);

            String sHTML = streamToString(in);
            in.close();

            //display this html in the browser

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return v;

    }

    private String streamToString(InputStream in) throws IOException {
        if(in == null) {
            return "";
        }

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

        } finally {

        }

        return writer.toString();
    }








}
