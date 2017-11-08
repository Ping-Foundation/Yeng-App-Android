package hsm.yeng.web;

import hsm.yeng.syllabus.dom.Syllabus;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by nikhil on 28/8/17.
 */

public interface SyllabusAPI {
    @GET("/syllabus/getChildById/{id}")
    Call<Syllabus> getChildById(@Path("id") String id);

    @GET("/Syllabus/{filePath}")
    Call<ResponseBody> downloadSyllabus(@Path("filePath") String fileUrl);
}
