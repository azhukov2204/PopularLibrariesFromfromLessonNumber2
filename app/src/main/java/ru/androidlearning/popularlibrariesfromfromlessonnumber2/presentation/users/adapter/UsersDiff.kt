package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity

object UsersDiff : DiffUtil.ItemCallback<GitHubUserEntity>() {
    override fun areItemsTheSame(oldItem: GitHubUserEntity, newItem: GitHubUserEntity): Boolean =
        oldItem.userId == newItem.userId &&
                oldItem.login == newItem.login

    override fun areContentsTheSame(oldItem: GitHubUserEntity, newItem: GitHubUserEntity): Boolean =
        oldItem == newItem
}
