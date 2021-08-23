package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "github_user_repo",
    foreignKeys = [ForeignKey(
        entity = GitHubUser::class,
        parentColumns = ["repos_url"],
        childColumns = ["repos_url"],
        onDelete = ForeignKey.CASCADE
    )],
    primaryKeys = ["id", "repo_url"],
    indices = [Index(value = ["repo_url"], unique = true),
    Index("repos_url")]
)
data class GitHubUserRepo(
    @ColumnInfo(name = "id") @SerializedName("id") val id: Int,
    @ColumnInfo(name = "name") @SerializedName("name") val name: String,
    @ColumnInfo(name = "full_name") @SerializedName("full_name") val fullName: String,
    @ColumnInfo(name = "repo_url") @SerializedName("url") val repoUrl: String,
    @ColumnInfo(name = "repos_url") var reposUrl: String
)
