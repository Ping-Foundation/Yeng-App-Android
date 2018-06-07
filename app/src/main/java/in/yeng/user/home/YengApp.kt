package `in`.yeng.user.home

import `in`.yeng.user.R
import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


class YengApp : Application() {

    override fun onCreate() {
        // For custom font
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Quicksand-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

}