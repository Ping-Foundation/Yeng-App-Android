package `in`.yeng.user.team.dom
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Team(
    @SerializedName("_id") val id: String,
    @SerializedName("updatedOn") val updatedOn: String,
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("__v") val v: Int,
    @SerializedName("groups") val groups: List<Group>
)

data class Group (
    @SerializedName("_id") val id: String,
    @SerializedName("updatedOn") val updatedOn: String,
    @SerializedName("group") val group: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("__v") val v: Int
): Serializable