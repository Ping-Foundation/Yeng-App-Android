package hsm.yeng.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import hsm.yeng.R;
import hsm.yeng.syllabus.dom.Syllabus;
import hsm.yeng.util.PreferenceManager;
import hsm.yeng.web.APIClient;
import hsm.yeng.web.SyllabusAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    SyllabusAPI syllabusAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int mUIFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        getWindow().getDecorView().setSystemUiVisibility(mUIFlag);

        Retrofit retrofit= APIClient.getClient();
        syllabusAPI=retrofit.create(SyllabusAPI.class);

        Call<Syllabus> syllabusCall= syllabusAPI.getChildById("Syllabus");
        syllabusCall.enqueue(new Callback<Syllabus>() {
            @Override
            public void onResponse(Call<Syllabus> call, Response<Syllabus> response) {
                Syllabus syllabus=response.body();
                ArrayList<String> childrens=syllabus.getChildrens();
                PreferenceManager.putStringArrray(SplashActivity.this,PreferenceManager.COURSE_PREF,childrens);
            }

            @Override
            public void onFailure(Call<Syllabus> call, Throwable t) {

            }
        });

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
