package `in`.yeng.user.splashscreen

import `in`.yeng.user.AnimUtil
import `in`.yeng.user.MainActivity
import `in`.yeng.user.R
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.intentFor


class SplashScreen : AppCompatActivity() {

    lateinit var logo: ImageView
    lateinit var logoText: ImageView
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        setContentView(R.layout.activity_splash_screen)

        logo = findViewById(R.id.logo)
        logoText = findViewById(R.id.logo_text)


        AnimUtil.fadeDown(logoText)
        AnimUtil.fadeUp(logo)

        handler = Handler().apply {
            postDelayed(
                    {
                        startActivity(intentFor<MainActivity>())
                        finish()
                    }
                    , 1500
            )
        }

    }

    override fun onPause() {
        super.onPause()
        finish()

    }


}
