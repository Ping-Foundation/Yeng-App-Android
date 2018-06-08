package `in`.yeng.user.syllabus.network

import `in`.yeng.user.syllabus.dom.SyllabusRes
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SyllabusReq {
    @GET("/syllabus/getChildById/{id}")
    fun getSyllabusList(@Path("id") id: String): Call<SyllabusRes>

    @GET("/SyllabusRequest/{filePath}")
    fun downloadSyllabus(@Path("filePath") fileUrl: String): Call<ResponseBody>
}