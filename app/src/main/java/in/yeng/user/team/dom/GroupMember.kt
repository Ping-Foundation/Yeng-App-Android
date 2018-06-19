package `in`.yeng.user.team.dom
import com.google.gson.annotations.SerializedName



data class GroupMember(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)