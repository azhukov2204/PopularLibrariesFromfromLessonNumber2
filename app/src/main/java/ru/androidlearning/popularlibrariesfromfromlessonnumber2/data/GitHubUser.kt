package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "github_users",
    primaryKeys = ["id", "repos_url"],
    indices = [Index(value = ["repos_url"], unique = true)]
)
data class GitHubUser(
    @ColumnInfo(name = "id") @SerializedName("id") val userId: Long,
    @ColumnInfo(name = "login") @SerializedName("login") val login: String,
    @ColumnInfo(name = "avatar_url") @SerializedName("avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "repos_url") @SerializedName("repos_url") val reposUrl: String,
    @ColumnInfo(name = "avatar_local_path") var avatarLocalPath: String?
)
