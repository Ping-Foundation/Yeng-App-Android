package hsm.yeng.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by arunima on 3/11/17.
 */

public class PreferenceManager {

    private static String PREF_NAME="yeng";
    public static String COURSE_PREF="courses";
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    private static void getReadInstance(Context context){
         sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }

    private static void getWriteInstance(Context context){
        getReadInstance(context);
        editor=sharedPreferences.edit();
    }

    public static void putStringArrray(Context context,String prefName, ArrayList<String> values){
        getWriteInstance(context);
        Gson gson=new Gson();
        String jsonText=gson.toJson(values);
        editor.putString(prefName,jsonText);
        editor.commit();

    }

    public static String[] getStringArray(Context context,String prefName){
        getReadInstance(context);
        Gson gson=new Gson();
        String jsonText=sharedPreferences.getString(prefName,null);
        String text[]=gson.fromJson(jsonText,String[].class);
        return text;
    }

}
