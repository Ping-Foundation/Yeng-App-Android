package `in`.yeng.user.Utilities

import android.text.format.DateUtils
import java.text.SimpleDateFormat


object DateHelper {

    fun getTimeStamp(date: String): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return dateFormat.parse(date).time
    }

    /*
    Get Date in Relative Scale like, 2 days ago , in 10 days ..
     */
    fun getRelativeDate(date: String): String {
        val now = System.currentTimeMillis()
        return DateUtils.getRelativeTimeSpanString(getTimeStamp(date), now, DateUtils.DAY_IN_MILLIS).toString()
    }
}