package `in`.yeng.user.syllabus.network

import `in`.yeng.user.network.APIClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception

object SyllabusAPI {

    fun getSyllabusList(id: String, func: (List<String>, List<String>, status: Int) -> Unit) {
        doAsync {
            try {
                val syllabusService = APIClient.withURL(APIClient.YENG_BASEURL).create(SyllabusReq::class.java)
                val call = syllabusService.getSyllabusList(id)
                val result = call.execute()
                val syllabusArray: List<String> = result?.body()?.children as MutableList<String>
                val filesArray: List<String> = result.body()?.files as MutableList<String>
                uiThread { func(syllabusArray, filesArray, result.code()) }
            }catch (e : Exception)  {
                uiThread { func(listOf(""), listOf(""), 404) }
            }
        }
    }
}