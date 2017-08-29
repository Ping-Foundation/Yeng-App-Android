package hsm.yeng.web;

import hsm.yeng.syllabus.dom.Syllabus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nikhil on 28/8/17.
 */

public interface SyllabusAPI {
    @GET("/syllabus/getChildById/{id}")
    Call<Syllabus> getChildById(@Path("id") String id);
}
