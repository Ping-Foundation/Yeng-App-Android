package `in`.yeng.user.Models.Requests

import `in`.yeng.user.Models.Responses.SyllabusResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SyllabusRequest {
    @GET("/syllabus/getChildById/{id}")
    fun getSyllabusList(@Path("id") id: String): Call<SyllabusResponse>

    @GET("/SyllabusRequest/{filePath}")
    fun downloadSyllabus(@Path("filePath") fileUrl: String): Call<ResponseBody>
}