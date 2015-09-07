package hsm.yengg.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import hsm.yengg.util.SessionController;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionController session = new SessionController(this);
        if (session.isFirstTimeRunning()) {
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
