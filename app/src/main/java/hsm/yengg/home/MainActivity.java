package hsm.yengg.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hsm.yengg.util.SessionController;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionController session = new SessionController(this);
        //It will show splash screen if the condition is true. It will show only first time if it work based on 'session' object,
       /* if (session.isFirstTimeRunning()) {*/
        if(true){
            Intent splash = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(splash);
            finish();
        } else {
            Intent home = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(home);
            finish();
        }


    }
}
