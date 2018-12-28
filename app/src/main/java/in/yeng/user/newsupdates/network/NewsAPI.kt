package `in`.yeng.user.newsupdates.network

import `in`.yeng.user.network.APIClient
import `in`.yeng.user.newsupdates.dom.NewsRes
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception

object NewsAPI {
    fun getNews(func: (List<NewsRes>?, status: Int) -> Unit) {
        doAsync {
            try {
                val newsService = APIClient.withURL(APIClient.YENG_BASEURL).create(NewsReq::class.java)
                val call = newsService.getNews()
                val result = call.execute()
                uiThread { result?.let { func(it.body(), result.code()) } }
            }catch (e:Exception)    {
                uiThread { func(null, 404) }
            }
        }
    }
}