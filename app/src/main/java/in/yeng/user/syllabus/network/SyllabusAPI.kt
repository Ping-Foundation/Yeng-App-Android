package `in`.yeng.user.syllabus.network

import `in`.yeng.user.network.APIClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

object SyllabusAPI {
    /*

Execute input blocks with (MutableList<String>, MutableList<String>) argument

Usage:

APIClient.getSyllabusList("id") { a, b ->
    process(a, b);   //
}

*/
    fun getSyllabusList(id: String, func: (List<String>, List<String>) -> Unit) {
        doAsync {
            val syllabusService = APIClient.withURL(APIClient.YENG_BASEURL).create(SyllabusReq::class.java)
            val call = syllabusService.getSyllabusList(id)
            val result = call.execute().body()
            val syllabusArray: List<String> = result?.children as MutableList<String>
            val filesArray: List<String> = result?.files as MutableList<String>
            uiThread { func(syllabusArray, filesArray) }
        }
    }
}