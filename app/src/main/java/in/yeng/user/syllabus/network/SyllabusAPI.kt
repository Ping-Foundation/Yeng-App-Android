package `in`.yeng.user.syllabus.network

import `in`.yeng.user.network.APIClient
import `in`.yeng.user.syllabus.dom.SyllabusReq
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
    fun getSyllabusList(id: String, func: (MutableList<String>, MutableList<String>) -> Unit) {
        doAsync {
            val syllabusService = APIClient.client.create(SyllabusReq::class.java)
            val call = syllabusService.getSyllabusList(id)
            val result = call.execute().body()
            val syllabusArray: MutableList<String> = result?.children as MutableList<String>
            val filesArray: MutableList<String> = result?.files as MutableList<String>
            uiThread { func(syllabusArray, filesArray) }
        }
    }
}