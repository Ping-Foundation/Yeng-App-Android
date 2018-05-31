package `in`.yeng.user

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


class YengApp : Application() {

    override fun onCreate() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Quicksand-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

}