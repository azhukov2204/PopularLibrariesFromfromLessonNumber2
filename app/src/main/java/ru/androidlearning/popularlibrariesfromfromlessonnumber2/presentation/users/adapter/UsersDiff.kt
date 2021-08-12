package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GithubUserEntity

object UsersDiff : DiffUtil.ItemCallback<GithubUserEntity>() {
    override fun areItemsTheSame(oldItem: GithubUserEntity, newItem: GithubUserEntity): Boolean =
        oldItem.userId == newItem.userId &&
                oldItem.login == newItem.login

    override fun areContentsTheSame(oldItem: GithubUserEntity, newItem: GithubUserEntity): Boolean =
        oldItem == newItem
}
