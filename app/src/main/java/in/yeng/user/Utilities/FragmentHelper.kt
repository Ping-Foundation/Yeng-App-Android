package `in`.yeng.user.Utilities

import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

object FragmentHelper {
    fun ReplaceFragment(fragment: Fragment, activity: AppCompatActivity, layout: Int, delayInMillis: Long) {
        Handler().postDelayed(
                { activity.supportFragmentManager.beginTransaction()
                            .replace(layout, fragment)
                            .commit()
                },
                delayInMillis
        )

    }

    fun RemoveFragment(fragment: Fragment, activity: AppCompatActivity, delayInMillis: Long) {
        Handler().postDelayed(
                { activity.supportFragmentManager.beginTransaction()
                            .remove(fragment)
                            .commit()
                },
                delayInMillis
        )
    }

    fun AddFragment(fragment: Fragment, activity: AppCompatActivity, layout: Int, delayInMillis: Long) {
        Handler().postDelayed(
                { activity.supportFragmentManager.beginTransaction()
                            .add(layout, fragment)
                            .commit()
                },
                delayInMillis
        )
    }
}