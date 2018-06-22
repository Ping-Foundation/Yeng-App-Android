package `in`.yeng.user.newsupdates.network

import `in`.yeng.user.network.APIClient
import `in`.yeng.user.newsupdates.dom.NewsRes
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/*
Execute input blocks with a List of items

Usage:

APIClient.getNews {
    process(it) //'it' is List<NewsandUpdatesResponse>
}
*/
object NewsAPI {
    fun getNews(func: (List<NewsRes>) -> Unit) {
        doAsync {
            val newsService = APIClient.withURL(APIClient.YENG_BASEURL).create(NewsReq::class.java)
            val call = newsService.getNews()
            val result = call.execute().body()
            uiThread { result?.let { func(result) } }
        }
    }
}