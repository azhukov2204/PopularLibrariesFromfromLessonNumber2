package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.R
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.databinding.TextViewItemBinding
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoEntity

class UserReposAdapter(private val itemClickListener: ItemClickListener) : ListAdapter<GitHubUserRepoEntity, UserReposAdapter.UserReposViewHolder>(UserReposDiff) {
    inner class UserReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: TextViewItemBinding by viewBinding(TextViewItemBinding::bind)

        fun bind(gitHubUserRepoEntity: GitHubUserRepoEntity) {
            binding.textViewItem.text = gitHubUserRepoEntity.name
            itemView.setOnClickListener { itemClickListener.onUserRepoClick(gitHubUserRepoEntity) }
        }
    }

    interface ItemClickListener {
        fun onUserRepoClick(gitHubUserRepoEntity: GitHubUserRepoEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserReposViewHolder =
        UserReposViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.text_view_item, parent, false)
        )

    override fun onBindViewHolder(holder: UserReposViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
