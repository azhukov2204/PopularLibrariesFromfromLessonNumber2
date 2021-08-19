package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoEntity

object UserReposDiff : DiffUtil.ItemCallback<GitHubUserRepoEntity>() {
    override fun areItemsTheSame(oldItem: GitHubUserRepoEntity, newItem: GitHubUserRepoEntity): Boolean =
        oldItem.id == newItem.id &&
                oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: GitHubUserRepoEntity, newItem: GitHubUserRepoEntity): Boolean =
        oldItem == newItem
}
