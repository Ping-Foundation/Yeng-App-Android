package hsm.yengg.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class used for manage Login/Logout/Splash screen(First enter)/Update notification with the help of Shared Preference.
 *
 * Created by nikhil on 23/8/15.
 */
public class SessionController {

    Context context;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private static final String PREF_NAME="pref";

    private static final String IS_FIRST_TIME="first_time";


    public SessionController(Context context){
        this.context=context;
        pref=context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

    }

    /**
     * Check the app running first time from shared preference.
     * @return true If the app running first time.
     */
    public boolean isFirstTimeRunning(){
        if(pref.getBoolean(IS_FIRST_TIME,true)){
            editor=pref.edit();
            editor.putBoolean(IS_FIRST_TIME,false);
            editor.commit();
            return true;
        }else{
            return false;
        }

    }
}
