package `in`.yeng.user.Models.Responses
import com.google.gson.annotations.SerializedName



data class SyllabusResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("__v") val v: Int,
    @SerializedName("files") val files: List<String>,
    @SerializedName("children") val children: List<String>
)