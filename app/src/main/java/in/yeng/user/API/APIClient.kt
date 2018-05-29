package `in`.yeng.user.API

import `in`.yeng.user.Models.Requests.SyllabusRequest
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    val BASE_URL = "https://www.yengapp.com"

    val client: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    fun getSyllabusArray(func: (List<String>) -> Unit) {
        doAsync {
            lateinit var syllabusArray: List<String>
            val syllabusCall = APIClient.client.create(SyllabusRequest::class.java)
            val call = syllabusCall.getSyllabusList("Syllabus")
            val result = call.execute().body()
            syllabusArray = result?.children ?: listOf("...")

            uiThread {
                func(syllabusArray)
            }
        }
    }

}
