package `in`.yeng.user.newsupdates

import `in`.yeng.user.R
import `in`.yeng.user.helpers.AnimUtil
import `in`.yeng.user.helpers.NetworkHelper
import `in`.yeng.user.network.APIClient
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.expanded_news_activity.*
import kotlinx.android.synthetic.main.expanded_news_content.*
import org.jetbrains.anko.toast
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.io.File

class ExpandedNews : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expanded_news_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        titleText.text = intent.getStringExtra("title")
        contentText.text = intent.getStringExtra("content").plus("\n\n\n\n")

        val attachmentLink: String? = intent.getStringExtra("attachment")

        if (attachmentLink.isNullOrEmpty()) {
            downloadAttachment.hide()
        } else {
            downloadAttachment.setOnClickListener {
                toast("Loading Attachment...")
                NetworkHelper.onPDFDownloaded(this, APIClient.YENG_BASEURL + "/" + attachmentLink!!.replace("public/", "")) {

                    val file = File(cacheDir, "pdfFile.pdf")
                    val sharedURI = FileProvider.getUriForFile(this, "in.yeng.user.PDFPROVIDER", file)

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(sharedURI, "application/pdf")
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

                    val activities = packageManager.queryIntentActivities(intent, 0)
                    if (activities.isEmpty())
                        toast("No PDF Viewers found. Get Adobe reader!..")
                    else
                        startActivity(intent)
                }
            }

            AnimUtil.fadeIn(titleText, 700)
            AnimUtil.fadeIn(contentText, 700)
            AnimUtil.fadeUp(downloadAttachment, 600, 300f)

        }
    }

    /*
   For showing Backbutton over Toolbar
    */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                finish()
        }
        return true
    }

    // For custom font
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


}
